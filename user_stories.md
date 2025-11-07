# User Story Template

        `**Admin Stories**`
**Admin Login**

Title:
As an admin, I want to log into the portal with my username and password, so that I can securely manage the platform.

Acceptance Criteria:

The system should allow the admin to enter a valid username and password.

The admin is authenticated using the stored credentials.

Upon successful login, the admin dashboard should be displayed.

Invalid credentials should show an error message.

Priority: High
Story Points: 3
Notes:

Use encrypted passwords in the database.

Implement authentication using Spring Security.

**2. Admin Logout**

Title:
As an admin, I want to log out of the portal, so that I can protect system access after use.

Acceptance Criteria:

A “Logout” option is visible on the dashboard.

Clicking it ends the admin’s active session.

The admin is redirected to the login page with a confirmation message.

Priority: High
Story Points: 2
Notes:

Session should expire automatically after inactivity.

**3. Add Doctors**

Title:
As an admin, I want to add new doctors to the portal, so that patients can view and book appointments with them.

Acceptance Criteria:

Admin can open a form to add a new doctor’s details (name, specialization, contact info, etc.).

All required fields must be validated.

The doctor’s profile is saved successfully in the database.

The new doctor appears on the “Doctors” list immediately after addition.

Priority: High
Story Points: 4
Notes:

Use form validation and JPA for saving doctor records.

**4. Delete Doctor Profile**

Title:
As an admin, I want to delete a doctor’s profile from the portal, so that I can remove outdated or incorrect data.

Acceptance Criteria:

Admin can view a list of registered doctors.

Admin can select a doctor and click “Delete.”

The system prompts for confirmation before deletion.

The doctor’s profile is permanently removed from the database.

Priority: Medium
Story Points: 3
Notes:

Ensure referential integrity if doctor data links to appointments.

**5. Track Monthly Appointments (via Stored Procedure)**

Title:
As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that I can track usage statistics.

Acceptance Criteria:

A stored procedure is available in MySQL that counts appointments grouped by month.

Admin can execute it manually through CLI or view results in the dashboard.

The report shows month-wise totals clearly.

Priority: Medium
Story Points: 2
Notes:

Optimize the stored procedure for large datasets.


      `**Patient User Stories**`
      
**1. View Doctors Without Login**

Title:
As a patient, I want to view a list of doctors without logging in, so that I can explore options before registering.

Acceptance Criteria:

The homepage displays a list of available doctors with their names, specialties, and basic info.

The list should be accessible without requiring login or registration.

The system should show a message or button encouraging the patient to register to book an appointment.

Priority: High
Story Points: 3
Notes:

Data fetched via a public REST API endpoint (e.g., /api/doctors/public).

Doctor list should load efficiently using pagination or lazy loading.

**2. Patient Registration**

Title:
As a patient, I want to sign up using my email and password, so that I can book appointments and manage my profile.

Acceptance Criteria:

A “Sign Up” form collects email, password, and optional personal details.

Passwords are securely hashed before saving to the database.

After successful registration, the patient can log in immediately or be redirected to the login page.

Error messages are shown for invalid or duplicate emails.

Priority: High
Story Points: 4
Notes:

Implement using Spring Security and JPA.

Add input validation for email format and password strength.

**3. Patient Login**

Title:
As a patient, I want to log into the portal, so that I can manage my appointments securely.

Acceptance Criteria:

The login page accepts registered email and password.

Successful login redirects to the patient dashboard.

Invalid credentials display an error message.

Sessions should remain active until logout or timeout.

Priority: High
Story Points: 3
Notes:

Use JWT tokens or Spring Session for authentication.

Store user roles for access control (Patient vs Admin).

**4. Patient Logout**

Title:
As a patient, I want to log out of the portal, so that I can secure my account after use.

Acceptance Criteria:

A “Logout” button should be available on the patient dashboard.

Logging out ends the current session and redirects to the homepage or login page.

The system should clear authentication tokens or cookies.

Priority: High
Story Points: 2
Notes:

Implement session timeout for inactive users.

**5. Book an Appointment**

Title:
As a patient, I want to log in and book an hour-long appointment with a doctor, so that I can consult them as per my schedule.

Acceptance Criteria:

The patient can choose a doctor, date, and available one-hour slot.

The system checks for conflicts before confirming the booking.

A confirmation message or email is sent after successful booking.

The appointment details are stored in the database.

Priority: High
Story Points: 5
Notes:

Implement appointment conflict check via backend validation.

Consider time zone consistency in booking logic.

`**Doctor User Stories**`

**1. Doctor Login**

Title:
As a doctor, I want to log into the portal, so that I can manage my appointments securely.

Acceptance Criteria:

The login form accepts a registered email/username and password.

Successful login redirects the doctor to their dashboard.

Invalid credentials display an error message.

Sessions remain active until logout or timeout.

Priority: High
Story Points: 3
Notes:

Implement authentication with Spring Security.

Use role-based access control (doctor role).

**2. Doctor Logout**

Title:
As a doctor, I want to log out of the portal, so that I can protect my data and prevent unauthorized access.

Acceptance Criteria:

A “Logout” option is available on the dashboard.

Clicking logout ends the current session.

The system redirects to the login or homepage.

Session tokens are invalidated upon logout.

Priority: High
Story Points: 2
Notes:

Implement session timeout for inactive users.

**3. View Appointment Calendar**

Title:
As a doctor, I want to view my appointment calendar, so that I can stay organized and manage my daily schedule efficiently.

Acceptance Criteria:

The dashboard displays all upcoming appointments in a calendar view.

Each appointment shows patient name, date, and time.

The doctor can switch between daily, weekly, and monthly views.

Appointments update automatically when new bookings occur.

Priority: High
Story Points: 4
Notes:

Use a calendar library in the frontend (e.g., FullCalendar.js).

Fetch data via REST API: /api/doctor/{id}/appointments.

**4. Mark Unavailability**

Title:
As a doctor, I want to mark my unavailability, so that patients can only book available time slots.

Acceptance Criteria:

The doctor can select specific dates/times to mark as unavailable.

Unavailable slots are stored in the database.

The booking system automatically blocks these slots for patients.

Confirmation message is displayed after saving.

Priority: High
Story Points: 5
Notes:

Implement backend logic to exclude unavailable slots in appointment scheduling.

Optional: allow recurring unavailability (e.g., every Sunday).

**5. Update Profile**

Title:
As a doctor, I want to update my profile with my specialization and contact information, so that patients have accurate and up-to-date details.

Acceptance Criteria:

Doctor can open a profile edit form.

Fields include specialization, experience, contact number, and clinic details.

Changes are saved to the database and reflected immediately.

System validates inputs before saving.

Priority: Medium
Story Points: 3
Notes:

Use JPA to update doctor data in MySQL.

Add audit tracking for profile changes.

