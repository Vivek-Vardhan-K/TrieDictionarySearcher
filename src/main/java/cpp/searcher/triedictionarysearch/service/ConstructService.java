package cpp.searcher.triedictionarysearch.service;

import cpp.searcher.triedictionarysearch.model.TrieNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
@Service
public class ConstructService {
    @Value("${fileLocation}")
    public String fileLocation;
    public  void constructTrie(TrieNode root, String fileName) throws FileNotFoundException {
        File file=new File(fileLocation+fileName);
        List<String> v = new ArrayList<String>();
        try(BufferedReader in=new BufferedReader(new FileReader(file))){
            String line;
            while ((line = in.readLine()) != null) {
                v.add(line);
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
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
