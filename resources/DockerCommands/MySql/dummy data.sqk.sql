INSERT INTO User (password, username, firstname, lastname, email, phone) VALUES
('password123', 'johndoe', 'John', 'Doe', 'johndoe@example.com', '1234567890'),
('password456', 'janedoe', 'Jane', 'Doe', 'janedoe@example.com', '0987654321'),
('password789', 'alexsmith', 'Alex', 'Smith', 'alexsmith@example.com', '5551234567'),
('securepass1', 'mikejohnson', 'Mike', 'Johnson', 'mikejohnson@example.com', '2223334444'),
('securepass2', 'emilyclark', 'Emily', 'Clark', 'emilyclark@example.com', '3334445555'),
('securepass3', 'sarahlee', 'Sarah', 'Lee', 'sarahlee@example.com', '4445556666'),
('securepass4', 'davidharris', 'David', 'Harris', 'davidharris@example.com', '5556667777'),
('securepass5', 'lisawilson', 'Lisa', 'Wilson', 'lisawilson@example.com', '6667778888');



INSERT INTO Product (name, description, price, stock, categoryName) VALUES
('Wireless Mouse', 'A high-quality wireless mouse.', 25.99, 100, 'Electronics'),
('Gaming Keyboard', 'Mechanical keyboard with RGB lighting.', 75.49, 50, 'Electronics'),
('Bluetooth Speaker', 'Portable speaker with excellent sound quality.', 45.00, 30, 'Audio'),
('Smartphone Case', 'Durable case for smartphones.', 15.99, 200, 'Accessories'),
('Rechargeable Batteries', 'AA rechargeable batteries, pack of 4.', 20.33, 150, 'Electronics'),
('Laptop Stand', 'Adjustable aluminum laptop stand.', 30.00, 80, 'Accessories'),
('Wireless Earbuds', 'Noise-canceling wireless earbuds.', 50.99, 120, 'Audio'),
('4K Monitor', '27-inch 4K UHD monitor.', 299.99, 40, 'Electronics'),
('External Hard Drive', '1TB portable hard drive.', 65.49, 60, 'Storage'),
('Fitness Tracker', 'Smart fitness tracker with heart rate monitor.', 99.99, 70, 'Wearables'),
('Portable Charger', '10,000mAh portable power bank.', 25.49, 90, 'Accessories'),
('Digital Camera', 'Compact digital camera with 20MP resolution.', 150.00, 25, 'Photography'),
('Desk Lamp', 'LED desk lamp with adjustable brightness.', 20.00, 100, 'Home & Office');



INSERT INTO CartItem (userId, productId, quantity) VALUES
(1, 1, 2), -- John Doe added 2 Wireless Mice
(1, 3, 1), -- John Doe added 1 Bluetooth Speaker
(2, 2, 1), -- Jane Doe added 1 Gaming Keyboard
(2, 4, 3), -- Jane Doe added 3 Smartphone Cases
(3, 5, 4), -- Alex Smith added 4 Rechargeable Batteries
(1, 6, 1), -- John Doe added 1 Portable Charger
(1, 8, 2), -- John Doe added 2 Desk Lamps
(2, 7, 1), -- Jane Doe added 1 Digital Camera
(2, 4, 2), -- Jane Doe added 2 External Hard Drives
(3, 3, 1), -- Alex Smith added 1 4K Monitor
(3, 5, 1), -- Alex Smith added 1 Fitness Tracker
(4, 2, 3), -- Mike Johnson added 3 Gaming Keyboards
(4, 1, 1), -- Mike Johnson added 1 Wireless Mouse
(5, 9, 2), -- Emily Clark added 2 Wireless Earbuds
(5, 10, 1), -- Emily Clark added 1 Laptop Stand
(6, 6, 2), -- Sarah Lee added 2 Portable Chargers
(6, 7, 1), -- Sarah Lee added 1 Digital Camera
(7, 5, 1), -- David Harris added 1 Fitness Tracker
(7, 3, 1), -- David Harris added 1 4K Monitor
(8, 8, 4), -- Lisa Wilson added 4 Desk Lamps
(8, 4, 1); -- Lisa Wilson added 1 External Hard Drive


