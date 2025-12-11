# oway-shipping

Shipping Take home

---

# 📦 Oway Shipping

A server-rendered logistics and shipment orchestration platform built with **Spring Boot**, **JTE templates**, **HTMX**, and **GraphHopper/JSPRIT** for route optimization.

---

## 🚀 Overview

Oway Shipping is a lightweight, high-performance shipping management platform that uses **server-side rendering** to deliver a fast, predictable, and secure user experience.

The application provides:

- Shipment creation and management
- Pending and completed shipment workflows
- Optimized routing using real-world geospatial coordinates
- Interactive components powered by **HTMX**
- Fully server-rendered UI with **JTE (Java Template Engine)**
- Backend services built with **Spring Boot** and **Spring Data JPA**

This architecture avoids SPA complexity while providing a reactive, modern user interface.

---

## 🖥️ Technology Stack

### **Backend**

- **Spring Boot 3**
- **Spring MVC**
- **Spring Data JPA (PostgreSQL)**
- **JTE (gg.jte) Template Engine**
- **HTMX for incremental UI updates**
- **GraphHopper / Jsprit** for vehicle routing and optimization

### **Frontend**

- **Tailwind CSS** for styling
- **HTMX** for dynamic partial updates
- **JTE** for server-side templates

### **Database**

- **PostgreSQL**
  Configured via `JDBC_URL`:

```
jdbc:postgresql://<host>:5432/postgres
```

---

## 🎨 Server-Side Rendering (SSR) with JTE

The UI is fully rendered on the server using **JTE**, a fast, type-safe template engine.
Benefits:

- No hydration overhead
- Significantly reduced JavaScript
- Templates are precompiled → extremely fast
- Strong typing between Java and HTML
- Easy SEO and accessibility

HTMX is used to update small parts of the page without reloading the entire UI.

Example:

```html
<button
  hx-post="/shipments/{id}/complete"
  hx-target="#shipment-{id}"
  hx-swap="outerHTML"
>
  Complete
</button>
```

The server returns a re-rendered JTE component, and HTMX replaces only that element.

---

## 🛣️ Routing & Optimization (GraphHopper + Jsprit)

Oway Shipping includes integrated route optimization using:

- **GraphHopper Location Index**
- **Jsprit VRP (Vehicle Routing Problem) Solver**

The routing service:

- Converts ZIP lat/lon into coordinates
- Builds a VRP problem with shipment pickup/delivery pairs
- Solves the optimized route
- Returns a structured `DisplaySolution` and `DisplayRoute` model to the UI

Output is displayed in a server-rendered right-side route panel.

---

## 📁 Project Structure

```
src/main/java/com/oway/shipment/
│
├── model/                # JPA domain models
├── shipment/             # Shipment controllers, services, repository
├── routing/              # Routing controller + VRP logic
├── parsing/              # ZIP CSV parsing into records
└── utils/                # Pageable and shared utilities
```

### Templates (JTE)

```
src/main/jte/
│
├── layout/               # Main layout wrapper
├── pages/                # Full SSR pages
└── components/           # Reusable components (shipments, routing)
```

---

## ⚙️ Routing Endpoint

Generate an optimized route for pending shipments:

```
GET /route
```

This builds the VRP, computes best solution, and renders:

```
components/routing/routing.jte
```

---

## 🧪 Development Setup

### 1. Clone the repo

```bash
git clone https://github.com/your-org/oway-shipping.git
cd oway-shipping
```

### 2. Configure environment

Create a `.env` or export:

```
JDBC_URL=jdbc:postgresql://localhost:5432/postgres
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

### 4. Access the UI

```
http://localhost:3000/pending
```

---

## 🧱 Key Features

### ✔ Server-rendered UI

Clean, fast, no SPA complexity.

### ✔ HTMX for interactivity

Partial-page updates with no frontend framework.

### ✔ Fast templating

JTE is precompiled and type-safe.

### ✔ Routing Optimization

Integrated VRP solving with GraphHopper/Jsprit.

### ✔ Zip-to-Geo Mapping

Zip CSV file converted into coordinates for routing.

### ✔ Shipment lifecycle

Create → Pending → Completed

---

## ✓ Future Enhancements

- Batch routing
- Multi-vehicle simulation
- Mapping visualization (Leaflet or Mapbox)
- Real distance/traffic routing (GraphHopper server API)
- Performance metrics on routing solve times

---
