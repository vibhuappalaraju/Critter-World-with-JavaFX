# Critter-World-with-JavaFX
Critter world simulation with JavaFX

In the Params.java, the dimensions of the world can be changed by modifying the world width and world height. Other paramters that can be modified are : 
walk energy cost (the energy it costs for a critter to walk)
run energy cost  (the energy it costs for a critter to run) 
rest energy cost (the energy it costs for a critter when it does not move) 
min_ eproduce energy  (the energy it costs for a critter to reproduce)
refresh_algae_count (the number of algae created every timestep of a critter)
photosynthesis_energy_amount (the energy of the algae) 
start energy (the amount of energy that is given to a critter by default)
look_energy cost (the energy it costs for a critter look if other critters are in its path)
	
	


In this project, a world of critters are created. There are different critters that can be created by the user.Each critter has different characteristics. For example, they can walk or run in random directions and when we click step the critters move. Each critter has a default energy and walking and running energy costs depend on the paramaters. After a step, if a critter lands on the position of another critter they both perform their fight functions. In their fight functions, the critter can attempt to walk or run away but if it is out of energy will die. If they both choose to fight, one randomly survives and gets half the losers energy. The loser is removed from the critter world. The user can also choose to see the statics of a critter and if they user clicks animation, it will show a step by step animation of the critters in the world.  
