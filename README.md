This is the official code repository for the project "Social TV Guide" created by Vasco Craveiro Costa, Diogo Pedroso and Fabio Martins for the "Technologies for Operational Information Systems" of the MSC. Computer Engineering course at ISCTE-IUL lectured by Carlos Costa during the 2014/2015 school year.

This project is intended to provide a proof-of-concept for a Recommendation system that uses a graph database to store it's data and to be an example implementation of a program using the Neo4j Database system.

You can download a compiled jar and the libs from this link:
https://mega.co.nz/#!qkw1yIpL!SsXqqZj5665VomWvXtkKqHv_hpS7Bnf6S7sAsStahDQ

In order to run this application you must have the address of a running Neo4j server, follow this link to learn how to setup the server:
http://neo4j.com/docs/stable/server-installation.html

After the server is up, run the "tvprograms.cypher" script provided in the project in the Neo4j shell utility. To do so in Linux/Mac run the following command:

```
#!shellscript

./home/user/neo4j-community-2.1.7/neo4j-shell -c < tvprograms.cypher
```