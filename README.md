
# BH Intern Task 4 - CSV Product Filter

## 📌 Task Description
The task is to **read product data from a CSV file**, filter the products based on their price, and save the filtered results into a new CSV file.  

- Input: `products.csv`  
- Condition: Products with **price greater than 1000**  
- Output: `expensive_products.csv`  

This task helps in practicing **File Handling** and **CSV operations** in Java.

---

## 🗂️ Project Structure
```

BH\_Intern\_Task4/
│── src/                 # Java source code
│── products.csv         # Input CSV file
│── expensive\_products.csv (generated after running)
│── .classpath
│── .project

```

---

## ⚙️ Requirements
- Java 8 or higher  
- Eclipse IDE (or any Java IDE)  

---

## ▶️ How to Run the Project
1. Open **Eclipse IDE**.  
2. Import the project (`BH_Intern_Task4`) into Eclipse.  
3. Place the file `products.csv` in the **root folder of the project**.  
4. Open the `Task4.java` file from `src`.  
5. Run the program:  
   - Right-click on `Task4.java` → **Run As → Java Application**.  
6. After successful execution:  
   - A new file `expensive_products.csv` will be generated in the same project folder.  

---

## 📂 Example
### Input: `products.csv`
```

ProductID,ProductName,Price
1,Laptop,55000
2,Mouse,500
3,Keyboard,1500
4,Mobile,12000

```

### Output: `expensive_products.csv`
```

ProductID,ProductName,Price
1,Laptop,55000
3,Keyboard,1500
4,Mobile,12000

```

---

## ✅ Conclusion
This task demonstrates:
- Reading CSV files in Java  
- Filtering data based on conditions  
- Writing results into a new CSV file  

---

👨‍💻 Developed as part of **BH Internship Task 4**
```


