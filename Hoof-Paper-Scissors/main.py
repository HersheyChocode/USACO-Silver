fileIn = open("hps.in", "r")
fileOut = open("hps.out", "w")

file = fileIn.read().splitlines()
file.pop(0)
wins = [0,0]

for line in file:
    cow1 = int(line.split()[0])
    cow2 = int(line.split()[1])
    if cow1==1:
        if cow2==3:
            wins[0]+=1
        if cow2==2:
            wins[1]+=1
    if cow1==2:
        if cow2==1:
            wins[0]+=1
        if cow2==3:
            wins[1]+=1
    if cow1==3:
        if cow2==2:
            wins[0]+=1
        if cow2==1:
            wins[1]+=1

fileOut.write(str(max(wins)))
