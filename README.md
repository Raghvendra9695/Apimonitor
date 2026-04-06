# 🚀 ApiHealth - Real-Time API Monitoring & Observability Dashboard

[![Status](https://img.shields.io/badge/Status-Active%20Development-orange)]()
[![Tech Stack](https://img.shields.io/badge/Tech--Stack-Java%20%7C%20Spring%20Boot%20%7C%20React-blue)]()

## 📌 1. The "Why" - Problem Statement
In modern microservices architectures, keeping track of API health, uptime, and performance metrics across multiple services is a major challenge. 

**The Pain Points:**
- **Silent Failures:** APIs go down without immediate alerts, leading to poor user experience.
- **Latency Issues:** Slow response times are hard to track without historical data.
- **Complexity:** Manually checking each endpoint's status code is inefficient and prone to error.

---

## 💡 2. The Solution
**ApiHealth** is a centralized monitoring dashboard that provides real-time visibility into your API ecosystem. It periodically pings registered endpoints, analyzes response times, and visualizes system health through an intuitive dashboard.

### Key Features:
- **Uptime Monitoring:** Automatic periodic health checks for all registered REST endpoints.
- **Performance Metrics:** Visualization of response time (Latency) trends over time.
- **Status Categorization:** Real-time status badges (UP / DOWN / DEGRADED).
- **Incident History:** Logging of downtime events for root cause analysis (RCA).

---

## 🛠 3. Technical Architecture
The system is built with a focus on high availability and low-latency data processing.

- **Frontend:** Built with **React.js, Vite, and Tailwind CSS** for a responsive, high-performance monitoring UI.
- **Backend:** Powered by **Java Spring Boot**, utilizing Scheduled Tasks for periodic health pings.
- **Persistence:** **PostgreSQL** for storing historical performance data and logs.
- **Observability:** Custom metrics collection for API response time analysis.



---

## 🚧 4. Project Status & Roadmap
This project is currently in **Active Development**.

- [x] **Frontend Architecture:** Completed and Deployed.
- [x] **UI/UX Design:** Responsive Dashboard with Status Badges.
- [ ] **Backend Microservices:** Currently refining the Spring Boot health check logic.
- [ ] **Integration:** Connecting live backend services with the frontend via WebSockets/REST.
- [ ] **Alerting System:** Integration with Email/Slack for instant downtime notifications.

---

## 📂 5. Getting Started
*(Note: Full backend integration is in progress)*

### Frontend Deployment:
The frontend is live and can be accessed here:  
🔗 **[INSERT YOUR LIVE NETLIFY/VERCEL LINK HERE]**

### Installation (Local Development):
```bash
# Clone the repository
git clone [https://github.com/Raghvendra9695/ApiHealth.git](https://github.com/Raghvendra9695/ApiHealth.git)

# Navigate to frontend
cd frontend
npm install
npm run dev
