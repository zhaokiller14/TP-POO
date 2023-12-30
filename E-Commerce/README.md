####E-CORP E-Commerce Application
Project Overview:

The E-CORP E-Commerce Application is a Java-based system that simulates an online shopping platform. The application includes several features to facilitate user interaction, product management, and secure authentication.
Implemented Features:
1. User Authentication:

    - The application provides secure user authentication with username and password.
    - Users can register as either an "Admin" or a "Customer."
    - Admins have access to additional features, requiring a secret code for verification (Hinted at right at the start of each execution of the code).
    - User credentials are stored in file ("user_database.txt") which provides the ability of the login feature if the user is already  registered. User role is also registered, the authentication process checks if they're an admin, a user, or a secret third thing (mentionned in the end)..

2. Product Management:

    - The system maintains an inventory of products with details such as name, price, quantity, and stock state with additional details based on the category of the product(Electronics,Book,Clothing).
    - Users can add, remove, and update product details: Admins edit the inventory products, while Customers can edit their shopping cart.

3. Shopping Cart:

    - Customers can add products to their shopping cart, specifying the quantity.
    - The shopping cart is stored separately and can be loaded from a file.
    - Customers can view and manage the items in their shopping cart.

4. Order Processing:

    - The application supports order processing, allowing customers to place orders from their shopping cart.
    - Customers can choose to order one product at a time or checkout to order all the products in their shopping cart.
    - Orders are recorded, and transactions are added to the order history.
    - An admin can access a customer's order history.
	
5. Inventory Management:

    - The inventory is saved and loaded from a file ("product_database.txt").
    - The inventory is constantly updated after each purchase/change in quantity with an update to product stock state that follows that
    - Products that are low stock or out of stock are highlighted when displaying the inventory

6. Dynamic Product Search and Filtering
    - Customers are allowed to search for products in the inventory filtering by Category, price, name and stock state of the product, they have the ability to filter by any or all of these fields.

7. Payment Processing
    - Implements the Strategy pattern through including the PaymentStrategy interface and PaymentContext class
    - Customers are allowed to choose between Paypal or credit card payment

8. Discounts and Promotions:

    - Discounts and promotions are implemented through a dynamic discount code system.
    - The PaymentContext class handles payment processing and applies a 10% discount for valid codes.
    - Each order of more than 5 items of the same product is provided with a 20% promotion

9. Database Implementation:

    - The application uses file-based storage for user data, product data, and order history.
    - User information is stored in "user_database.txt," and product details are stored in "product_database.txt."
    - Added a "customer" directory/folder which stores a folder for each customer with their username as file name. These folders store - the 	Shopping cart products and Order history of each customer. They are loaded from their respective files on each execution.

10. Easter egg (Bonus):
    
    - Triggers when the username and password of the user trying to login are the first and last name of the main character of a show about hacking and mental illness (the name of the console application is a hint). Try it at your own risk.

