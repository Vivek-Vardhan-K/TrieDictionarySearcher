package cpp.searcher.triedictionarysearch.service;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FinderService {
//    private String globs;
    private static List<String> global=new ArrayList<>();

    private static TrieNode findFollowUpNode(String s,TrieNode root){
        TrieNode temp=root;
        for(int i=0;i<s.length();i++){
            if(temp.mp.containsKey(s.charAt(i))){
                temp=temp.mp.get(s.charAt(i));
            }
            else return null;
        }
        return temp;
    }

    private static void recursionPush(TrieNode root,StringBuilder s){ //DFS
        TrieNode temp=root;
        if(root.endOfWord.equals(Boolean.TRUE)){
            global.add(s.toString());
        }
        for(Map.Entry<Character,TrieNode> entry: temp.mp.entrySet()){
            s.append(entry.getKey());
            recursionPush(entry.getValue(),s);
            s.deleteCharAt(s.length()-1);
        }
    }

    public static List<String> startsWith(String s,TrieNode root){
        global=new ArrayList<>();
        TrieNode temp=findFollowUpNode(s,root);
        StringBuilder sb=new StringBuilder(s);
        recursionPush(temp,sb);
        return global;
    }
}
