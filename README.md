# Diabetes-Search
Neural network that learns how to diagnose diabetes

The project's idea was to create a neural network from scratch. I didn't want to use already existing classes that would do 
half work for me so I decided to do it this way. I'm using a dataset that contains various data about patients and the information
if they have diabetes or not. The network uses 75% of the dataset to learn and then we check how competent it is with diagnosing diabetes.
Overall performance of the network is not so bad given the fact that dataset is only so small (768 records). It's diagnosis success rate
oscilates around 75-80% which is pretty good. In the future I plan to add some fancy GUI for that project. Another room for improvement is
better scalability of NeuralNetowrk class since it has hardcoded backpropagation and other computations it is quite inefficient to use for
other neural netowrks right now. I plan to change it in the future.
