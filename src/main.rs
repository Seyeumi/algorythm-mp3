const FACEBOOK: &str = "facebook";
const FACEHANDLER: &str = "facehandler";
const MOM: &str = "mom";
const DAD: &str = "dad";

const AMOUNT_OF_CHARACTERS: i32 = 26;

fn main() {
    let mut trie = Trie {
        root: Node {
            children: vec!(),
            edge_label: String::from(""),
            is_end: false
        }
    };

    trie.insert(FACEBOOK);

    trie.insert(FACEHANDLER);
    println!("{}", trie.root.children[0].edge_label);

    //let v: Vec<Option<i32>> = vec![Option::None, Option::None];
}



struct Node {
    children: Vec<Node>,
    edge_label: String, // char ll
    is_end: bool,
}


struct Trie {
    root: Node,
}

impl Trie {
    fn insert(&self, word: &str) {
        let mut root = &self.root;

        let mut children = &mut root.children;
        let mut chars = word.chars();

        if children.len() == 0 {
            // No childs. Add one
            children.push(Node {
                children: vec!(),
                edge_label: String::from(chars.as_str()),
                is_end: true
            })
        } else {
            loop {
                match chars.next() {
                    Option::Some(current_char) => { // f
                        let mut i = 0;
                        while i < children.len() { // facehandler
                            let child = children[i]; // facebook
        
                            let mut label = child.edge_label.chars(); // acebook

                            let first_char = label.next().unwrap(); // f
        
                            if first_char == current_char {
                                root.edge_label = first_char.to_string(); // f
                                root = child; //
                                root.children.push(Node {
                                    children: vec!(),
                                    edge_label: String::from(label.as_str()),
                                    is_end: true
                                });
                                break;
                            } else {
                                i += 1;
                            }
                        };
                    },
                    Option::None => {
                        break;
                    }
                }
            }
        }
}
}