rfile = open("kruskal.inp", "r")
wfile = open("kruskal.out", "w")

(nodeCount, numOfCase) = rfile.readline().strip().split(" ")

print(nodeCount, " ", numOfCase)


num = int(numOfCase)
node = int(nodeCount)

graph = []
for i in range(num) :
    (start, end, weight) = rfile.readline().strip().split(" ")
    graph.append((start, end, weight))

graph.sort(key = lambda x: x[2])

mst = []
p = [0]

for i in range(0, node) :
    p.append(i)

def find(u) :
    if u != p[u] :
        p[u] = find(p[u])
    return p[u]

def union(u, v) :
    root1 = find(u)
    root2 = find(v)
    p[root2] = root1

tree_edges = 0
mst_cost = 0

while True:
    if tree_edges == node-1 :
        break

    u, v, wt = graph.pop(0)

    if find(u) != find(v) :
        union(u, v)
        mst.append((u, v))
        mst_cost += wt
        tree_edges += 1

print("최소 신장 트리 : ", mst)

print("최소 신장 트리 가중치 : ", mst_cost)

rfile.close()
wfile.close()