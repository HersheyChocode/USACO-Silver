fileIn = open("guess.in", "r")
fileOut = open("guess.out", "w")

file = fileIn.read().splitlines()
file.pop(0)

animals = []
conditions = []
yesses = []

for x in range (len(file)):
    i = file[x]
    conditions.append([])
    animals.append(i.split()[0])
    for y in range(int(i.split()[1])):
        conditions[x].append(i.split()[y+2])
for x in range(len(animals)):
    animal = animals[x]
    condition = conditions[x]
    print(condition)
    counter = 0
    for y in condition:
        for z in conditions:
            if z!=condition:
                if y in z:
                    counter+=1
print(yesses)
        
fileOut.write(str(max(yesses)))
