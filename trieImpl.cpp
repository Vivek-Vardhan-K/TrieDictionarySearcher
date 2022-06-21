#include<bits/stdc++.h>

using namespace std;


class TrieNode {
public:
	map<char, TrieNode*> mp;
	bool endOfWord;
	TrieNode() {
		this->endOfWord = false;
	}
};

vector<string> global;

void construct(TrieNode* root, vector<string>& W) {
	for (string word : W) {
		TrieNode* temp = root;
		for (int i = 0; i < word.length(); i++) {
			if (temp->mp.find(word[i]) != temp->mp.end()) {
				temp = temp->mp[word[i]];
			}
			else {
				temp->mp[word[i]] = new TrieNode();
				temp = temp->mp[word[i]];
			}
			if (i == word.length() - 1) {
				temp->endOfWord = true;
			}
		}
	}
}

TrieNode* findFollowUpNode(string &s, TrieNode* root) {
	TrieNode* temp = root;
	for (int i = 0; i < s.length(); i++) {
		if (temp->mp.find(s[i]) != temp->mp.end()) {
			temp = temp->mp[s[i]];
		}
		else return NULL;
	}
	return temp;
}

void recurPrint(TrieNode* root,string s) {
	TrieNode* temp = root;
	if(root->endOfWord){
		global.push_back(s);
	}
	for(auto itr=temp->mp.begin();itr!=temp->mp.end();itr++){
		s.push_back(itr->first);
		recurPrint(itr->second,s);
		s.pop_back();
	}
}




string startsWith(string s, TrieNode* root) {
	TrieNode* temp = findFollowUpNode(s, root);
	recurPrint(temp,s);
	for(auto w: global){
		cout<<w<<"\n";
	}
	return  "===========End of Result=============";
}

signed main() {
	int n;
	cin >> n;
	vector<string> words(n);
	for (int i = 0; i < n; i++) {
		cin >> words[i];
	}
	TrieNode* root = new TrieNode();
	construct(root, words);
	string s = "ca";
	cout<<"Search results for "<<s<<'\n';
	cout<<"-----------------------------"<<'\n';
	cout << startsWith(s, root);


}

