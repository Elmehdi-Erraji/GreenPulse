# Carbon Consumption Tracker

## Overview

The **Carbon Consumption Tracker** is a Java-based console application that allows users to manage their profiles, track carbon consumption, and generate detailed consumption reports. The application is designed to help users analyze their habits by tracking their daily, weekly, and monthly carbon consumption, allowing them to visualize their impact on the environment.

## Features

- **User Management**: 
  - Create, update, delete, and retrieve user profiles.
  - Each user has a unique ID, name, and age.

- **Carbon Consumption Tracking**:
  - Add carbon consumption records for users, specifying the start and end date of each consumption period.
  - Track multiple carbon consumption records for each user.

- **Consumption Report Generation**:
  - Generate consumption reports on a daily, weekly, or monthly basis.
  - Report consumption based on the inserted data, even when the dates are irregular or separated.

## Project Structure

### Classes Overview

1. **User.java**: 
   - Represents a user in the system.
   - Contains attributes like user ID, name, age, and a list of consumption records.
   - Provides methods to manage consumption records.

2. **Consumption.java**: 
   - Represents a carbon consumption record.
   - Includes attributes like start date, end date, and the amount of carbon consumed.

3. **UserService.java**: 
   - Core service class for managing users and carbon consumption records.
   - Provides methods to create, update, delete users, and add carbon consumption records.
   - Supports generating reports (daily, weekly, and monthly) based on consumption data.

4. **Main.java**: 
   - The entry point of the program.
   - Provides a console-based menu for interacting with the application.

## How to Use

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/carbon-consumption-tracker.git
