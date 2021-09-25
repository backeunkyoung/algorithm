rfile = open("family.inp", "r")
wfile = open("family.out", "w")

testCount = int(rfile.readline().strip())

#print testCount

for treeLine in range(testCount) :
    tree = {}
    rootList = set()

    lineCount = int(rfile.readline().strip())
    for i in range(lineCount-1) :
        (parent, child) = rfile.readline().strip().split(" ")
        # print line
        # parent = line[0]
        # child = line[1]

        if parent in tree :
            temp = tree[parent]
            temp.append(child)
            tree[parent] = temp
        else :
            tree[parent] = [child]
            rootList.add(parent)

        if child not in tree : tree[child] = []

        if child in rootList : rootList.remove(child)

    #print tree
    #print keyList
    #print childList

    parentArray = rootList
    depth = 0

    while True :
        childList = []
        for key in parentArray :
            if key in tree :
                childList += tree[key]

        #print childList

        parentArray = childList
        depth += 1
        if len(parentArray) == 0 : break


    #print depth
    wfile.write("%d\n" % depth)
rfile.close()
wfile.close()