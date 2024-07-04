# FRQ 4
## About:
- Creating the methods `getNextLoc` and `sumPath`.
- `getNextLoc` takes in two ints, row and col, and returns a Location object that also has two ints, row and col.
- A neighbor is either the place below or to the right of the current location. `getNextLoc` finds the smaller neighbor.
- `sumPath` repeats using `getNextLoc` until the final location is reached. On the way it adds up the numbers of each neighbor and returns the sum at the end.
## Struggles:
- Returning the sum.
- An int variable has to be made with the sum of the current location.
- When returning the variable you also have to add the sum of the final location.
## Helpful Prior Experiences:
- Leaning how classes interact with each other.
- Learning how to use a 2-d array.
## What I Learned:
- When creating a sum variable, it's not always best to make it equal to zero.
