# StarbucksTeamProject

Three team members:

Mrinalini GopalaKrishnan, Megha Nair, Sahana Innanje

## Individual APIs and contributions :
1) Mrinalini Gopalakrishnan - Order Use Case - Manage Order API, Get Menu API. 
Worked on setting up EC2 load balancer and deployment on AWS, contributions to trello board as a team member, 
Integration testing using postman.
2) Megha Nair - Payment use case - Pay for an order API, Get payments for a user,Get payments for a reward card,
Contributions to trello board as a team member, Integration testing using postman. 
3) Sahana Innanje - Registration and login use case - Registration API, Login API using email and password. 
Add Card use case - Add Card API, Get all cards of a user API. 
Worked on setting up EC2 instance load balancer on AWS, created github and trello boards, worked on docker setup using ECR and ECS. 

## Agile team details :
3 member team . 1 week sprint cycle . Estimated hours and burndown. Used trello for agile implementation. 
Created stories and tasks on trello . 

## Trello board : 
![Trello agile board](agile%20board%20on%20trello.png?raw=true "trello agile board")

![sprint cycle](sprint%20cycle.png?raw=true "sprint cycle")

![sprint estimates](hours%20estimaation%20points.png?raw=true "sprint estimates")

![burndown](burndown%20starbucks.png?raw=true "burndown") 

## XP value we used in our team : COMMUNICATION
We communicated face to face daily for the last week before starting the work on that day. We even had daily stand up and reporting. 
We discussed our blockers and any help which is needed. The team members contributed and helped others when it was known that someone was blocked. We discussed the requirements with each other and made changes on what component has to be built. 

## Technologies used :

Spring Boot REST Webservices with embedded tomcat for local testing

Cloud  Services : AWS - EC2 instances and load balancer with mysql

Database : MYSQL

Testing tool : Postman

## Architecture diagram :

![architecture](architecture.jpg?raw=true "architecture")

## Use cases:
1) Registration and login -

Registration and login api using email and password. Validation during login to check if email or password exists. If email and password doesn't exist, throw error. 

2) Adding cards for users

Add card api using email and credit card basic authorization for VISA,MAASTERCARD and AMEX for credit card number and cvv . This would ideally be sent to payment gateway API(not implemented) . Card id and a code are also sent for creation of reward card. 
Get card api to get all the cards for an email. 

3) Management of order
Get Menu API to see all the menu details available to the user. 
Manage order API to create an order and generate a total bill amount for the items added.

4) Payment for an order and payment history
Payment API to pay for an order based on order id and bill amount. If insufficient balance, then validation throwws error to the user. 
Get Payments API for a user to get all payments for the user.
Get Payments API for a card to get all payments for a card. 

## Deploy API to AWS in an Auto Scaled EC2 Cluster with Load Balancer
Created two EC2 instances . This was put in TARGET GROUPS  which were assigned to a load balancer with two instances size. 
Appropriate TCP, SSH AND HTTP security groups were created and attached to the AWS load balancer and also, instances. 
Our servers were up and we could access the load balancer as shown in the demo. We used postman to hit these instances and load balancer. 
Example : http://starbucks202-alb-may10-574270762.us-east-2.elb.amazonaws.com/v1/starbucks/getCards/sahana.innan@sjsu.edu
http://ec2-52-14-15-13.us-east-2.compute.amazonaws.com:8080/v1/starbucks/getCards/sahana.innan@sjsu.edu

## Deploy API to AWS as Docker Containers in Amazon Containers
A docker image was created from the Springboot JAR. Spring boot jaar used a dockerfile which is attached in github. 
Then a docker container was created in local instance for testing. Once tested, docker image was pushed to ECR repository. 
147119161124.dkr.ecr.us-east-2.amazonaws.com/starbucksecr
A starbucks cluster was created with a task and this container image in ECS. The cluster was started up in ECS with the required task 
instance.

## Frequency of commits - https://github.com/sinnanje/StarbucksTeamProject/commits/master

We have issues in our github insights as it is not showing up proper commit history. The above link has all the commits which were done by team members for the last week. 
Sahana's name appears as SARAH ddue to eclipse commits from her computer which is named SARAH. 





