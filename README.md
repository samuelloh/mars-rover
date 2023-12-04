# Mars Rover Application

This application simulates a 1 or more 'Mars Rovers' initiated with
specific 2D coordinates(x,y) and a direction, then takes in commands to move the Rover(s).

## Use Cases

## Single Rover

#### Input

First argument is the number of Rovers:

1. `1`

Then, the Mars Rover takes in 2 `String` arguments:

1. (x, y) coordinates, followed by a starting direction (N: North, S: South, E: East, W: West).
   Each value is separated by a
   comma
    1. E.g. `"3,4,N"`
2. Movement commands to be executed sequentially (each command is separated by a comma)
    1. E.g. `“f,f,r,f,f”`

#### Output

It then prints out the final coordinates and final direction of the rover.

E.g:

`Final Coordinate: 5, 6` \
`Final Direction: East`

## Multiple Rovers

#### Input

First argument is the number of Rovers:

1. `3`

Then, the Mars Rover takes in `String` arguments of multiples of 2.

Total no. of remaining arguments is `(no. of rovers) * 2`.
Each set of argument is separated by whitespace E.g:

`3,4,N f,f,r,f,f 1,1,S f,f,r,r,r,r,b,b 4,4,N b,b,b,b,b,b,r,l`

#### Output

It then prints out the final coordinates **in the order of the rover arguments entered**:








