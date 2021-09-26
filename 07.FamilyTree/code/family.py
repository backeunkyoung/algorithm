
rfile = open("6.inp", "r")
wfile = open("family.out", "w")

testCount = int(rfile.readline().strip())

#print testCount
import time
print (time.localtime())
for treeLine in range(testCount) :
    tree = {}
    childList = []
    keyList = []

    lineCount = int(rfile.readline().strip())
    #print(lineCount)
    for i in range(lineCount-1) :
        (parent, child) = rfile.readline().strip().split(" ")
        # print line
        # parent = line[0]
        # child = line[1]
        keyList.append(parent)
        childList.append(child)

        if parent in tree :
            temp = tree[parent]
            temp.append(child)
            tree[parent] = temp
        else :
            tree[parent] = [child]

    #print tree 

    parentArray = list(set(keyList) - set(childList))
    depth = 0
    #print root

    #print(parentArray)
    # break
    while True :
        nowChild = []
        for key in parentArray :
            if key in tree :
                nowChild += tree[key]

        #print nowChild
        
        
        parentArray = nowChild
        
        depth += 1
        # print(depth)
        if len(parentArray) == 0 : break


    # print depth
    wfile.write("%d\n" % depth)
    # break
print (time.localtime())
rfile.close()
wfile.close()


