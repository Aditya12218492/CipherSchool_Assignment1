# CipherSchools QA Engineer Intern – Practical Assignment

This repository contains the **Automation and Performance Testing deliverables** for the CipherSchools QA Engineer Intern assignment.

The objective of this assignment was to test a practice web application and evaluate its quality from an **EdTech perspective**, where products represent courses and the cart represents an enrollment basket.

The testing approach focused on identifying functional issues that could impact a learner’s journey such as authentication problems, course discovery failures, and enrollment workflow issues.

---

# Application Under Test

Practice Software Testing Web Application

URL  
https://with-bugs.practicesoftwaretesting.com

This application intentionally contains bugs and is designed for software testing practice.

---

# Deliverables

Deliverables **1, 2, and 3** (Manual Test Cases, Bug Reports, and Test Summary Report) are submitted separately as a document.

This repository contains:

- End-to-End Automation Script (Selenium with Java)
- API Load Testing (JMeter)
- Load Simulation Script (k6)
- Prometheus-compatible metrics output

---

# Project Structure

```
cipherqa-assignment
│
├── automation
│   └── selenium-java
│       ├── pom.xml
│       └── src
│
├── performance-testing
│   ├── jmeter
│   │   ├── search-api-test-plan.jmx
│   │   └── summary-report.png
│   │
│   └── k6
│       ├── search-load-test.js
│       ├── k6-results.png
│       └── prometheus-metric.png
│
└── README.md
```

---

# E2E Automation

Automation was implemented using **Selenium WebDriver with Java**.

### Automated Learner Journey

The automation script simulates a learner workflow and performs the following steps:

1. Navigate to the application URL using environment configuration
2. Register a new user account
3. Log in using the newly created credentials
4. Search for a course/product
5. Open the product detail page
6. Add the product to the enrollment basket
7. Verify that the basket count increases

### Tech Stack

- Java  
- Selenium WebDriver  
- Maven  

---

# JMeter API Load Testing

JMeter was used to test the **Product Search API endpoint**.

### Test Configuration

- 10 concurrent users
- 60 seconds execution
- API endpoint: `/products/search?q=hammer`

### Assertions Implemented

- Response status code validation
- Response body validation
- Response time assertion

The execution results are included as a **Summary Report screenshot**.

---

# k6 Load Simulation

k6 was used to simulate load on the search API.

### Test Configuration

- Ramp up to **20 virtual users in 10 seconds**
- Sustain load for **30 seconds**
- Ramp down over **10 seconds**

### Thresholds

- 95% of requests must complete under **2 seconds**
- Error rate must remain below **1%**

### Results

The test executed successfully with:

- **0% request failures**
- **p(95) response time = 792ms**, which is below the defined threshold

---

# Prometheus-Compatible Metrics

k6 exposes metrics that are compatible with Prometheus monitoring systems, including:

- `http_req_duration`
- `http_req_failed`
- `vus`
- `iterations`

The screenshot included in this repository demonstrates the **http_req_duration metric captured during load test execution**.

---

# Key Observations During Testing

During testing, several functional issues were identified that could impact a learner's experience.

### Search and Cart Functionality Disappear After Login

When the user is not logged in, the application displays the search bar and cart options in the navigation menu. However, after logging in, these options disappear from the interface.

This behavior prevents the user from searching for courses or adding items to the enrollment basket, which breaks the expected learner workflow.

From an **automation perspective**, this issue also affects the automated learner journey.

The automation script follows this flow:

```
Login → Search Course → Open Course Detail → Add to Cart
```

However, since the **search functionality becomes unavailable after login**, the automation script cannot continue with the search step and the remaining steps in the workflow would fail.

This indicates a **major UI/navigation issue** because authenticated users should still be able to discover courses and enroll in them.

This issue was reported in the bug report with **High severity** as it impacts the core platform functionality.

---

# Author

Aditya Raj  

QA Engineer Intern Candidate
