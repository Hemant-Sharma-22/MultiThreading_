## 💰 Thread-Safe Wallet System (Java)

### 📌 Overview

This project is a Thread-Safe Wallet Service implemented in Java that supports concurrent operations like creating users, crediting, debiting, and fetching balances. The system ensures data consistency and prevents race conditions when multiple threads access the same user.

---

### 🚀 Features

* Create user with initial balance
* Get current balance
* Credit amount to wallet
* Debit amount with insufficient balance check
* Thread-safe operations using ReentrantLock
* Supports concurrent access using multiple threads
* O(1) average time complexity using ConcurrentHashMap

---

### 🧠 Key Concepts Used

* Multithreading (Thread)
* Synchronization (ReentrantLock)
* Race condition handling
* Critical section protection
* Fine-grained locking (per-user lock)
* Efficient data structure (ConcurrentHashMap)

---

### ⚙️ Design Approach

Each user contains:

* A balance
* A dedicated lock (ReentrantLock)

A thread-safe map stores users:

* ConcurrentHashMap<String, User>

Per-user locking ensures:

* High concurrency
* No global lock bottleneck
* Safe updates on the same user

All critical operations (credit, debit, getBalance) are protected using locks.

---

### 🧪 Example Scenario

createUser("u1", 100)

Thread-1 → debit(50)
Thread-2 → debit(70)

Only one transaction succeeds and the final balance remains consistent.

---

### 🏗️ Architecture

Client Threads
↓
Wallet Service
↓
ConcurrentHashMap<UserId, User>
↓
User Object (balance + lock)

---

### ⚡ How to Run

Compile:
javac Main.java

Run:
java Main

---

### 📊 Output Example

T1: success
T2: failed
Final Balance: 50

---

### 🔥 Challenges Solved

* Prevented race conditions in concurrent updates
* Ensured atomic debit operations
* Avoided global locking for better scalability
* Maintained consistency under multi-threading

---

### 🏆 Learnings

* Practical use of Java concurrency tools
* Importance of synchronization in shared data
* Designing scalable and thread-safe systems

---

### 🚀 Future Improvements

* Add transfer functionality
* Use ReadWriteLock for optimization
* Integrate database
* Build REST APIs

---

### 👨‍💻 Author

Hemant Sharma
