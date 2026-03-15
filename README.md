# MedSync – AI Powered Hospital Management System

MedSync is a scalable hospital management system designed to streamline healthcare operations using modern backend technologies and AI integration. The system manages doctors, patients, appointments, hospital staff, and medical records while providing AI-assisted discharge summaries and prescription recommendations.

This platform is built using Spring Boot microservices architecture and integrates cloud storage, authentication, notification services, and AI-powered medical assistance.

---

## 🚀 Features

### Doctor Management
- Admin can register and manage doctors
- Doctors can log in and view their schedules
- Doctors can access patient medical history
- AI-assisted diagnosis and prescription suggestions

### Patient Management
- Receptionists can register new patients
- Doctors can update patient diagnoses
- Patients receive appointment confirmation emails

### Appointment Management
- Schedule, update, and cancel appointments
- Doctors can view daily appointment schedules
- Automated appointment reminders

### Staff & Hospital Management
- Manage hospital staff (receptionists, nurses)
- Manage hospital beds and room allocation

### Notifications System
- Email notifications for appointments
- Daily appointment schedule emails for doctors

### Discharge Summary Generation
- Generate discharge summaries in PDF format
- Securely store PDFs in AWS S3
- Download reports via secure links

### AI-Powered Features
- AI-powered diet and prescription suggestions
- AI-assisted discharge summary generation
- Integration with Spring AI (OpenAI)

---

# 🏗 System Architecture

The application follows a **microservices architecture** to ensure scalability and modularity.

### Microservices

- Authentication Service
- Doctor Service
- Patient Service
- Appointment Service
- Notification Service
- Storage Service

Each service is independently designed and communicates through REST APIs.

---

# 🧰 Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- Spring AI
- Microservices Architecture

### Frontend
- HTML
- CSS
- JavaScript
- Thymeleaf

### Database
- MySQL

### Cloud & Storage
- AWS S3

### Authentication
- JWT Authentication

### Notification Service
- SendGrid Email Service

### PDF Generation
- Thymeleaf
- Flying Saucer

---

# 👤 User Roles

### Admin
- Manage doctors
- Manage staff
- Configure system settings

### Receptionist
- Register patients
- Book appointments
- Manage hospital beds
- Generate discharge summaries

### Doctor
- View appointments
- Access patient records
- Update diagnosis
- Prescribe medication

---

# 📂 Project Structure

```
medsync-ai-hospital-management-system
│
├── auth-service
├── doctor-service
├── patient-service
├── appointment-service
├── notification-service
├── storage-service
│
├── frontend
│
└── docs
```

---

# 🔐 Security

- JWT-based authentication
- Role-based access control
- Secure API endpoints

---

# ☁️ Cloud Integration

- AWS S3 used for storing patient discharge summaries
- Secure access links for downloading documents

---

# 📈 Key Highlights

- AI-assisted medical recommendations
- Cloud-based medical record storage
- Automated hospital workflow management
- Scalable microservices architecture

---

# 🎯 Future Improvements

- Kafka event-driven architecture
- Real-time hospital analytics dashboard
- ML-based disease prediction
- Patient mobile application

---

# 👨‍💻 Author

Developed as a full-stack healthcare management platform using modern backend technologies and AI integration.
