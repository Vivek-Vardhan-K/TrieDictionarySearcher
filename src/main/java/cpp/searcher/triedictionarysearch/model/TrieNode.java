package cpp.searcher.triedictionarysearch.model;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character,TrieNode> mp=new HashMap<>();
    public Boolean endOfWord;
    public TrieNode(){
        this.endOfWord=Boolean.FALSE;
    }
}
