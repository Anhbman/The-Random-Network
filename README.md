An application to (visually) demonstrate  the random network generation problem

a. Random network model

b. Erdos-renyi model

c. Watts-Strogatz model

d. Barabasi-Albert model

#!/bin/bash

#File: tree-md

tree=$(tree -tf --noreport -I '*~' --charset ascii $1 |
       sed -e 's/| \+/  /g' -e 's/[|`]-\+/ */g' -e 's:\(* \)\(\(.*/\)\([^/]\+\)\):\1[\4](\2):g')

printf "# Project tree\n\n${tree}"
