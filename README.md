Interview Feedback Management System (IFMS) 📌 Overview:- The Interview Feedback Management System (IFMS) is a web-based platform designed to streamline interview processes within an organization. It enables HR managers to schedule interviews, assign interviewers, collect feedback, and make data-driven hiring decisions. It also enables the interviewer to see the interviewes assigned and it gets notified when the interview is assigned to him/her. Interviewer submits the feedback after evaluation which is then evaluted by HR_Manager.

🚀 Features:- User Authentication (HR Managers & Interviewers) Role-Based Access Control (RBAC) Interview Scheduling & Management Real-Time Interview Feedback Collection Skill Ratings & Decision Making API-Driven Architecture Future Machine Learning Integration (For Interview Analysis & Predictions)

🏗️ System Architecture:- The system follows a 3-tier architecture with future ML integration: Frontend: Vue.js + Fetch API Backend: Java 17 (Spring Boot) Database: PostgreSQL ML Module (Future Integration): Python (Flask for API)

📂 Tech Stack:- Component Technology Frontend -> Vue.js, Fetch API Backend -> Java 17, Spring Boot Database -> PostgreSQL Authentication -> JWT API Docs -> Postman Mail Smpt Server ->MailHog_windows_amd64.exe Machine Learning (Future) Python (Flask, Scikit-learn)

🔧 Installation & Setup:- Prerequisites Java 17 Node.js & npm PostgreSQL Maven Mailhog(user command -> ./MailHog_windows_amd64.exe to install )

Backend Setup:-

Clone the repository
git clone https://github.com/repo_url/interview-feedback-system.git

Navigate to backend
cd backend
served on port(9090)

Build & Run the backend
mvn clean install mvn spring-boot:run

Frontend Setup:-

Navigate to frontend
cd frontend
served on port(8080)

ML Setup
cd ml_model
run the command flask run
served on port(5000)

Install dependencies
npm install

Start the frontend
npm run dev

📝 API Documentation:- The API is documented using Postman Collection.Which is attached with the project

🛠️ ER Diagram:- 🚀 Future Enhancements ML-based Interview Feedback Prediction Advanced Analytics Dashboard Integration with Video Interview Platforms

🤝 Contributors:- Name:-Ujjwal Verma Email:- ujjwalvarma6948@gmail.com
