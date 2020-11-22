# My Online Bakery Store

## An introduction

This is a Java application that mimics the experiencing of managing an online bakery store where user can log in as a manager 
to manage the items on your menu and modify the items' prices and quantity. 

The Java application also mimics the experience of ordering on the online bakery store 
where user can can log in as a customer to add the items into the shopping list and get the total price of the items.

## User Stories
- As a user (both manager and customer), I want to be able to view the items in different categories on the menu
- As a manager/shop owner, I want to be able to add or remove the items on my menu
- As a manager/shop owner, I want to be able modify an item's price and quantity on my menu.
- As a customer, I want to be able to select the items in different categories and add it to my order list/shopping cart.
###### Data Persistence User Stories
- As a user, I want my items on the menu to be saved automatically upon exiting the program.
- As a user, I want my items on the menu to be load automatically upon opening the program.
###### Phase 4 Task 2
- I have chosen to test and design a class in my model package to be robust. 
- The class Item and its two methods setPrice and setQuantity is redesigned to be robust for the task.
###### Phase 4 Task 3
- If I have more time on this project, I would remove the BakedGoods and Drinks class from my project 
and create an enum interface so that more categories can be created in the future and do not need
further creation of classes that extends Item
- Additionally, I would separate MyMenu into two classes since MyMenu is having two functionalities.
One class for editing the items on the menu and another for returning the items on the menu.
 