# Natrual-language-Interface-for-relational-database

## Mentors

1. Anup Pokhrel
2. Suman Adhikari

---

## College/University 
1. Campus /College : Amrit Campus
2. University	  : Tribhuvan University

---

##  		Submitted Year		
** 2013-12-29 **

---

## Project Summary

This project approaches problem by carrying out a mapping between Natural Language (NL) and SQL syntactic structures. The mapping is automatically derived by applying unsupervised machine learning algorithms. In particular the project exploit linguistic dependencies in the natural language question and the database metadata to build a set of plausible SELECT, WHERE and FROM clauses enriched with meaningful JOINS. Then, combine all the clauses to get the set of all possible SQL queries, producing candidate queries to answer the question.

Purpose	:Query database in natural

Programming Language used	:Java

Database	:Mysql

Api Used		:stanfordcorenlp (version 3)

*This is the final year project that We have did. And this is focused on realtaion database.*	

## Insatllation and configuration:	
Go to the stanfordcorenlp site and download the stanfordcorenlp version 3.2 or higher and extract the downloaded package.
Import the libraries extracted to your project and the libraries needed are:		
1. stanford-corenlp-3.2.0.jar	
2. stanford-corenlp-3.2.0-models.jar	

After successfully import of the stanfordcorenlp libraries now you need to import the 
mysqllibrary so download the mysql driver for java and integrade it with this project 

Now there is the file with sql extention so import the sql file to your mysql server and lunch project successfully.
