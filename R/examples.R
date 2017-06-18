#This is code to demonstrate simple plotting and basic runs over Tempo data with R 
#Created and copyrighted by Mangobanaani 2017, licence under GNU General Public License v3

# first, let's try getting sum of hours grouped by billing key

options(gsubfn.engine = "R")
library(sqldf)
t1 <- as.data.frame(values)

sqldf("select sum(hours) as hours, billing_key, sum(hours*120) as eur from t1 group by billing_key order by sum(hours), billing_key")

-- 

# average time spent per billing key

options(gsubfn.engine = "R")
library(sqldf)
library(ggplot2)
d1 <- sqldf("select avg(hours) as hours, billing_key from t1 where billing_key!='null' group by billing_key order by billing_key, sum(hours)")

ggplot(d1, aes(x=billing_key, y=hours, fill=hours)) +	
      geom_bar(stat="identity", position="dodge") +
      geom_text(aes(label=billing_key), hjust=1.5, colour="white",
                position=position_dodge(0.9), size=rel(1.3))+coord_flip()+
                scale_x_discrete(limits = rev(levels(d1$billing_key)))+
                ggtitle("Average lenght of single task by customer")+
                xlab("Customer")+
                ylab("Hours")

# plot sum of hours by billing key from year 2015
# notice the format used with the dates, as default engine used by R is sqlite

d2_3 <- sqldf("select sum(hours) as hours, billing_key from t1 
where billing_key!='null' and strftime('%Y-%m-%d', work_date * 3600 * 24, 'unixepoch') >'2015-01-01' 
and strftime('%Y-%m-%d', work_date * 3600 * 24, 'unixepoch') <'2015-12-31' 
group by billing_key order by sum(hours)")


ggplot(d2_3, aes(x=billing_key, y=hours, fill=billing_key,ylab="Customer",xlab="Hours")) +	
     geom_bar(stat="identity", position="dodge") +
     geom_text(aes(label=hours), hjust=1.5, colour="white",
     position=position_dodge(0.9), size=rel(1.5))+coord_flip()+
     ggtitle("Total number of hours by customer 2015")+
     xlab("Customer")+
     ylab("Hours")

# regular plot timeseries with regression line

v1 <- as.Date(t1$work_date) 
v2 <- as.double(t1$hours) 
v.data <- data.frame(v1,v2)
ggplot(v.data, aes(x=v1, y=v2,fill=v2,colour=v2)) + geom_point(size=1.5)+
ggtitle("Hours per day 2015-2017 w/ regression")+xlab("year")+ylab("hours")+geom_point()+stat_smooth(method=lm, level=0.95)

