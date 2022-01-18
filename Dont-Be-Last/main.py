fileIn = open("notlast.in", "r")
fileOut = open("notlast.out", "w")

file = fileIn.read().splitlines()

cows = [["Bessie",0],["Daisy",0],["Elsie",0],["Gertie",0],["Annabelle",0],["Maggie",0],["Henrietta",0]]

for x in range(1, len(file)):
    name = file[x].split()[0]
    for i in range (7):
        if name in cows[i]:
            cows[i][1]+=int(file[x].split()[1])
amtMilk = []

for x in range(7):
  amtMilk.append(cows[x][1])
amtMilk.sort()

m = min(amtMilk)
# newMilk = []
# for x in range(7):
#   if cows[x][1]!=m:
#     newMilk.append(cows[x][1]) 
# newMilk.sort()
# amtMilk = newMilk

while(m in amtMilk):
  amtMilk.remove(m)

if len(amtMilk)==0:
  amtMilk=[0]

m = min(amtMilk)

if amtMilk.count(m)>1 or amtMilk.count(m)==0:
  fileOut.write("TIE\n")
  print("TIE")
else:
  for x in range(7):
        if cows[x][1]==m:
            fileOut.write(cows[x][0]+"\n")
            print(cows[x][0])