# MyShop - Desktop Application

## Overview
**MyShop** is a **Java Swing** desktop application that simulates an online shop, following the **Model-View-Controller (MVC)** architectural pattern. The application connects to a **PostgreSQL** database using **JDBC**, providing authentication, product browsing, shopping cart functionality, and order management.

## Key Features
- **User Authentication:** Sign up and log in as either a client or an admin.
- **Product Browsing:** Categorized product listings with search and filter options.
- **Shopping Cart:** Add and remove items, proceed to checkout.
- **Order Management:** View order history and order details.
- **Reviews System:** Add and view product reviews.

## Getting Started

### Prerequisites
- **Java JDK 8** or higher
- **PostgreSQL** (for database storage)
- **JDBC Driver for PostgreSQL**
- **IDE** (e.g., IntelliJ, Eclipse) or command-line environment

### Installation
1. **Clone the Repository:**
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to the Project Directory:**
   ```bash
   cd myshop
   ```
3. **Set Up the Database:**
   - Create a PostgreSQL database named `myshop_db`.
   - Execute the provided SQL schema to create necessary tables.
   - Update the database credentials in the **JDBC configuration**.

4. **Compile the Project:**
   ```bash
   javac -d bin src/**/*.java
   ```
5. **Run the Application:**
   ```bash
   java -cp bin com.myshop.Main
   ```

## Usage
### 1. Authentication
- Users can **sign up** as **clients** or **admins**.
- The only restriction is that **usernames must be unique**.
- After successful signup, users are redirected to the login page.
- Upon entering valid credentials, the main menu is displayed.

### 2. Main Menu
- Displays **four category buttons** for filtering products.
- A **ribbon bar** at the top allows users to:
  - View the **cart**.
  - View their **orders**.
  - **Search** for products (navigates to product details if found).
  - **Filter** products (requires refresh after modification).

### 3. Product Details
- Displays detailed information about a selected product.
- Users can:
  - Navigate **back**.
  - **Add a review**.
  - **View reviews**.
  - **Add the product to the cart**.

### 4. Shopping Cart
- Displays products added by the user.
- Users can:
  - **Remove** products from the cart.
  - Navigate **back to the main menu**.
  - Proceed to **checkout**.

### 5. View Orders
- Displays a list of all orders placed by the client.

### 6. Add Review
- Users can **rate a product (1-5)** and submit a review.
- Each client can submit **only one review per product**.

### 7. View Reviews
- Displays all reviews submitted by clients.

### 8. View Order
- Allows clients to **review** an order before **finalizing the purchase**.

## Future Enhancements
- **Admin Features:** Inventory management, user control, and sales reports.
- **Enhanced UI:** Improve visuals and usability.
- **Order Tracking:** Enable real-time order tracking.
- **Payment Integration:** Add payment processing for real-world transactions.
