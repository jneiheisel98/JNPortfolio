#include <iostream>
#include  <fstream>
#include <vector>
#include <string>
#include <sstream>
#include <bits/stdc++.h>
#include "DisjSets.h" 
#include "Edge.h"
using namespace std;
void split_str(std::string const &str, const char delim, std::vector <std::string> &out){
	std::stringstream s(str);
	std::string s2;
	while(std::getline(s,s2, delim)){
		out.push_back(s2);
	}
}

int main(){
	std::vector<Edge> edgeLib;
	const char delim = ' ';
	string line, splitLine;
	//cout << "before while loop";
	while (getline(cin, line)){
	//	std::cout << line<<std::endl;
		std::vector <std::string> out;
	split_str(line, delim, out);

	int start = stoi(out.at(0));
	int end = stoi(out.at(1));
	int weight = stoi(out.at(2));
	Edge e;
	e.node.start = start;
	e.node.end = end;
	e.node.weight = weight;
	edgeLib.push_back(e);
	}
	std::vector<Edge> orderedLib;
	std::vector<Edge> finalLib;
	int max_vert = 0;
	for (int index = 0; index < edgeLib.size(); index++){
		int swap = 0;
		int doGreaterThan = 0;
		Edge e1;
		e1.node.end = edgeLib.at(index).node.end;
		e1.node.start = edgeLib.at(index).node.start;
		e1.node.weight = edgeLib.at(index).node.weight;
		if(index >=1){
			doGreaterThan = 1;	
				}
		if( e1.node.end > max_vert){
		max_vert = e1.node.end;
		}
		if(e1.node.start > max_vert){
		max_vert = e1.node.start;
		}
		   
	for (int count = 0; count <edgeLib.size(); count++){
	Edge eLeft;

	//check that the node is less than e1, but greater than eleft, and noe equal to either one
	if(index ==2){
		//cout << "current end" << std::endl;
		//cout << edgeLib.at(count).node.end<<std::endl;
		//cout << "current start" << std::endl;
		//cout << edgeLib.at(count).node.start<<std::endl;
		//cout << "current weight" << std::endl;
		//cout << edgeLib.at(count).node.weight<<std::endl;
		//cout << "E1 end" << std::endl;
		//cout << e1.node.end<<std::endl;
		//cout << "E1 start" << std::endl;
		//cout << e1.node.start<<std::endl;
		//cout << "E1 weight" << std::endl;
		//cout << e1.node.weight<<std::endl;
		//cout << "Eleft Start:"<< std::endl;
		//cout<<orderedLib.at(index-1).node.start<<std::endl;
		//cout <<"ELEFT End: "<<std::endl;
		//cout << orderedLib.at(index-1).node.end << std::endl;
		//cout << "Eleft Weight: "<< std::endl;
		//cout << orderedLib.at(index-1).node.weight << std::endl;
		//cout<< doGreaterThan<<std::endl;
		//cout << swap << std::endl;
                }
	if(doGreaterThan == 1){
	eLeft.node.end = orderedLib.at(index-1).node.end;
	eLeft.node.start = orderedLib.at(index-1).node.start;
	eLeft.node.weight = orderedLib.at(index-1).node.weight;
	
	//check if the current edge is greater than the edge to the left and less than what is currently in e1
	if((edgeLib.at(count).node.weight > eLeft.node.weight)){
		if((e1.node.weight > edgeLib.at(count).node.weight )||((e1.node.weight == eLeft.node.weight) && (e1.node.end == eLeft.node.end) && (e1.node.start == eLeft.node.start))){
		swap = 1;
		}
	if(index == 2){
	//cout << "I was triggered" << std::endl;
	}
	}
	if((edgeLib.at(count).node.weight== eLeft.node.weight)&& (edgeLib.at(count).node.start != eLeft.node.start)&& (edgeLib.at(count).node.end != eLeft.node.end)){
	swap = 1;
		if(index ==2){
		//cout << "I was triggered" << std::endl;
	}
	}
	}
	
	if(doGreaterThan==0){
	//go for the least amount of something. 
	if (e1.node.weight > edgeLib.at(count).node.weight){
	swap = 1;
	}
	}
	
	if(swap ==1){
	e1.node.end = edgeLib.at(count).node.end;
	e1.node.start = edgeLib.at(count).node.start;
	e1.node.weight = edgeLib.at(count).node.weight;
	//cout << "E1 new end" << std::endl;
	//cout << e1.node.end<<std::endl;
	 //cout << "E1 new start" << std::endl;
	 //cout << e1.node.start<<std::endl;
	  //cout << "E1 new weight" << std::endl;
	  //cout << e1.node.weight<<std::endl;
	  swap = 0;
	}
	
	if(count == edgeLib.size()-1){
	orderedLib.push_back(e1);
	}
	
	}	
	}

	for (Edge side: orderedLib){
	//cout << "Weight:";
	//cout<< side.node.weight;
	//cout << side.node.start;
	//cout << side.node.end;
	//cout<<std::endl;
	}
	 for (Edge side: edgeLib){
		// cout << "Weight:";
		// cout<< side.node.weight;
		// cout << side.node.start;
		// cout << side.node.end;
		// cout<<std::endl;
	}
	 //cout << "Create the new disjsets thing";
	 int edgeLibVert = 0;
	 int set1, set2;
	 DisjSets ds { max_vert+1};
	 //cout << finalLib.size();
	 //cout << edgeLibVert;
	 while(finalLib.size() != max_vert){
	 Edge side= orderedLib.at(edgeLibVert);
	 //cout << side.node.end;
	 //cout << side.node.start;
	 set1 = ds.find(side.node.start);
	 set2 = ds.find(side.node.end);
	 //cout<<set1;
	 //cout<<set2;
	 if(set1 != set2){
	 	finalLib.push_back(side);
		ds.unionSets(set1, set2);
	 }
	 edgeLibVert = edgeLibVert+1;
	 }
	 cout << "The set of edges in the MST is:" << std::endl;
	 int weight = 0;

	 for (Edge lado: finalLib){
	 cout << "[" << lado.node.start<<","<<lado.node.end<<"]"<< " weight: "<<lado.node.weight<<std::endl;
	 weight+=lado.node.weight;
	 }
	 cout<<"The total cost is: "<<weight<<std::endl;


	return 0;
}

