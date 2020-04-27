import java.io.File
import java.nio.file.Paths

fun main() {
    val shakespeareList: Array<String> = File("${Paths.get("").toAbsolutePath()}/src/shakespeare.txt").toTypedArray()

    val trie = Trie()
    for (word in shakespeareList) {
        trie.insert(word)
    }

    println(trie.search("treasure"))
    println(trie.search("blabla"))
    println(trie.search("die"))
    /*
    trie.insert("Facebook")
    trie.insert("Facehandler")
    trie.insert("Facemachine")
    trie.insert("Face")
    trie.insert("cat")
    trie.insert("cato")
    trie.insert("done")
    println(trie.search("Facehand"))
    */
}


class Node(val children: MutableList<Node> = mutableListOf(), var edgeLabel: String = "", var isEnd: Boolean = false)


class Trie(private val root: Node = Node()) {

    fun insert(word: String) {
        var root = root;
        if(root.children.isEmpty()) {
            root.children.add(Node(edgeLabel = word, isEnd = true))
        } else {
            var i = 0;

            while(i < word.length) {
                var currentWordChar = word.substring(i..i)
                val childFound = foundChild(root.children, currentWordChar)

                if(childFound == null) {
                    root.children.add(Node (edgeLabel = word.substring(i until word.length), isEnd = true))
                    break
                } else {
                    val nodeChildLabel = childFound.edgeLabel
                    val restOfString = nodeChildLabel.substring(1 until nodeChildLabel.length)
                    if(restOfString.isNotEmpty()) {
                        childFound.children.add( Node(edgeLabel = restOfString, isEnd = true))
                        childFound.edgeLabel = currentWordChar
                        childFound.isEnd = false
                    }
                    root = childFound
                    i++
                }
            }
            root.isEnd = true
        }
    }

    fun search(word: String): Boolean {
        var root = root
        var i = 0
        while(i < word.length) {
            val currentWordChar = word.substring(i..i) //

            val foundChild = foundChild(root.children, currentWordChar) ?: return false

            if(foundChild.edgeLabel.length > 1 && foundChild.isEnd) {
                val restOfWord = word.substring(i until word.length)
                return restOfWord == foundChild.edgeLabel
            }
            root = foundChild
            i++

        }
        return true
    }
}

fun foundChild(children: MutableList<Node>, wordChar: String): Node? {
    var child: Node? = null

    var index = 0
    while (child == null && index < children.size) {
        if(wordChar == children[index].edgeLabel.substring(0..0)) {
            child = children[index]
            break
        }
        index++
    }
    return child
}