
---

## Table of Contents
- [Student Placement Management System](#student-placement-management-system)
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [File Structure](#file-structure)
  - [Log_in](#log_in)
  - [Integration](#integration)
  - [Student](#student)
  - [Coordinator](#coordinator)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

---
# Student Placement Management System

The Student Placement Management System is designed to streamline student placement processes efficiently. It provides a comprehensive platform for tracking and organizing student placements.
## Project Overview

This system automates tasks such as student registration, placement tracking, and reporting, helping educational institutions manage placements effectively. It is built using PHP for the backend, HTML, CSS, and JavaScript for the frontend, and MySQL for the database.

## Technologies Used

- *Backend:* PHP
- *Frontend:* HTML, CSS, JavaScript
- *Database:* MySQL
- *Other Tools:* XAMPP## File Structure

Below is a brief description of each file and its purpose:

### Log_in
- **`login_form.php`:** Main login page code.
- **`login.css`:** Styles for the login page.
- **`login.js`:** Functionality for the login page.

### Integration
- **`config.php`:** Connects to the local database.
- **`database.php`:** Holds user registration data.

### Student
- **`home.html`:** Student dashboard home page.
- **`listing.php`:** Displays jobs listed by coordinators.
- **`jobapp.html`:** Allows students to apply for jobs.
- **`submit_application.php`:** Submits job applications.
- **`submit_cv.php`:** Uploads CVs with applications.
- **`internship-listing.html`:** Lists internships (under development).
- **`profile.html`:** Displays student profiles (not integrated).
- **`tracking.html`:** Tracks submitted applications (not implemented).
- **`discussion.html`:** Discussion area for students.
- **`about.html`:** Website information.
- **`faq.html`:** Frequently Asked Questions.

### Coordinator
- **`coordinator.php`:** Coordinator dashboard.
- **`job-opening.php`:** Adds job openings.
- **`schedual-interview.php`:** Schedules interviews.
- **`view_application.php`:** Displays uploaded CVs.
## Installation

1. Install XAMPP from [Apache Friends](https://www.apachefriends.org/).
2. Start Apache and MySQL services.
3. Access localhost in your browser.
4. Create a database using phpMyAdmin. [Tutorial](https://youtu.be/co-xyHRdHRg?si=153f68Ak-3igAwcS).
## Usage

1. Access the system at localhost.
2. Register as a student or coordinator.
3. Follow on-screen instructions to manage placements.
## Contributing

Contributions are welcome! Please submit a pull request with your changes and include a brief description.

---
## Acknowledgement

Team Members **[Sana K S,Om Desai,Rishikesh Raj, Swapnil Sharma].**

This layout provides a clear structure for your README file, helping users understand your project's purpose, components, and how to use it. You can customize it further based on your specific needs and project details.