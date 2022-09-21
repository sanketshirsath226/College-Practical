#include<iostream>
#include<vector>
#include <algorithm>
#include<math.h>
using namespace std;
#define maxSize 7;
class TransmittedData {
    public:
    string strData;
    int dataSize;
    int paritySize;
    vector<int> data;
    TransmittedData(){
        dataSize = 0;
        paritySize = 0;
    }

    /*Accept User Data*/        
    void getData(){
        cout<<"Enter Data:";
        getline(cin,strData);
        splitData();
    }

    /*Convert String to Vector<int>*/
    void splitData(){
        for(int i=0;i<strData.length();i++){
            dataSize++;
            switch(strData[i]){
                case '0':
                    data.push_back(0);
                    break;
                case '1':
                    data.push_back(1);
                    break;
                default:
                    dataSize--;
            }
        }
    }

    void addparityBit(){
        vector<int> index;
        vector<int> temp;
        index = findParityIndex();
        for (int i = 0; i < data.size(); ++i)
        {
            if((find(index.begin(), index.end(), i) != index.end())){
               temp.push_back(-1);
            }
            temp.push_back(data.at(i));
        }
        data.clear();
        copy(temp.begin(), temp.end(), back_inserter(data)); 
        for (auto i = temp.cbegin(); i != temp.cend(); ++i)
            cout << *i << " ";
    }
    
    vector<int> findParityIndex(){
        vector<int> parityIndex;
        paritySize = maxSize - dataSize;
        for(int i=0;i<paritySize;i++){
            parityIndex.push_back(pow(2,i));
        }
        return parityIndex;
    }

    
    /* Display Data */
    void displayData()
    {
        cout<<"Data Size : "<<dataSize<<endl;
        cout<<"Data is : ";
        for (auto i = data.cbegin(); i != data.cend(); ++i)
            cout << *i << " ";
    }
};

int main()
{
    TransmittedData td;
    td.getData();
    td.addparityBit();
    td.displayData();
    return 0;
}