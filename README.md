# McMAHON-S-BURGERS: Restaurant Orders Simulation
## Overview
MMBurgers is a Java-based simulation project that models a restaurant's order processing system. The simulation efficiently manages customer arrivals, order processing, and griddle utilization using various data structures. The project is organized into classes and interfaces, providing a modular and extensible design for handling different aspects of the restaurant operations.

## Project Structure
1. MMBurgers.java: The main class that implements the simulation logic and interfaces with the MMBurgersInterface.
2. MMBurgersInterface.java: An interface defining the methods and functionalities that the MMBurgers simulation must implement.
3. maxheap.java: A class implementing a max heap data structure used for managing billing queues.
4. minheap.java: A class implementing a min heap data structure used for managing events in the simulation.
5. minheapwaiting.java: A class implementing a min heap data structure used for managing customers waiting for griddle space.
6. minheapevents.java: A class implementing a min heap data structure used for managing events in the simulation.

## Simulation Features
1. Customer Arrival and Ordering:
> Customers arrive with unique IDs, arrival times, and the number of burgers they want to order.
> The arrival time of each customer is tracked, and the order is placed in the billing queue.
2. Billing Queue Management:
> The billing queues are managed using a max heap to efficiently allocate customers to the available queues based on their size.
3. Griddle Utilization:
> The griddle has a maximum capacity (M) for cooking burgers simultaneously.
> Events are triggered for placing orders on the griddle, and customers are processed based on griddle availability.
4. Event-driven Simulation:
> The simulation progresses through discrete events, such as customer arrivals, order placement, and griddle usage, advancing time as needed.
5. Efficient Data Structures:
> Various min and max heap data structures are employed for efficient event management, billing queue allocation, and griddle space utilization.
## Usage
1. Setting Parameters:\
Use the setK(int k) method to set the number of billing queues.\
Use the setM(int m) method to set the maximum capacity of the griddle.
2. Customer Arrival:\
Use the arriveCustomer(int id, int t, int numb) method to simulate customer arrivals.
3. Time Advancement:\
Use the advanceTime(int t) method to advance the simulation to a specific time.
4. State Queries:\
Retrieve customer state, griddle state, griddle wait, and customer wait time using appropriate methods.
5. Average Wait Time:\
Obtain the average wait time per customer after the simulation completes using the avgWaitTime() method.
