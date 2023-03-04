package cpp.searcher.triedictionarysearch.service;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ConstructService {
    
    public  void constructTrie(TrieNode root, String fileName) throws FileNotFoundException {
        
        String str=getResourceFileAsString(fileName);
        String[] sArr=str.split("\n");
        
        List<String> v=new ArrayList<>();
        for(int i=0;i<sArr.length;i++){
            v.add(sArr[i]);
        }
        for(String s: v){
            TrieNode temp=root;
            for(int i=0;i<s.length();i++){
                if(temp.mp.containsKey(s.charAt(i))){
                    temp=temp.mp.get(s.charAt(i));
                }
                else{
                    temp.mp.put(s.charAt(i),new TrieNode());
                    temp=temp.mp.get(s.charAt(i));
                }
                if(i==s.length()-1){
                    temp.endOfWord=Boolean.TRUE;
                }
            }
        }
        System.out.println("Done Construction");
        
        
    }
    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }
    
    public static InputStream getResourceFileAsInputStream(String fileName) {   
        ClassLoader classLoader = ConstructService.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
