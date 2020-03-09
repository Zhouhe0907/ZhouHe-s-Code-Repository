#include <iostream>
using namespace std;
int main()
{
    cout<<"\n亲爱的勇士呐！欢迎来到幸存者游戏！(๑‾ ꇴ ‾๑)"
        <<"\n-------------------------------------"
        <<"\n游戏规则如下：\n游戏玩家投票选择一个不幸数字，然后为每名玩家分配随机连续的号码"
        <<"\n随后从一号玩家开始轮流报数"
        <<"\n报到不幸数字的玩家淘汰出局，由下一位玩家再次从'1'开始报数，直至决胜出一名幸运玩家！\n";

    bool replay = true;
    while(replay)
    {
        int yesOrNo;
        if(yesOrNo == 1)
            cout<<"\n-----------------";

        cout<<"\n请输入玩家人数：";
        int num;
        cin>>num;
        cout<<"请输入投票得出的不幸数字：";
        int quit;
        cin>>quit;


        int people[num];
        for(int i = 0;i < num;i++)
            people[i] = i+1;
        int flag = 0;

        int peopleLeft = num ,count = 0;
        int peopleSNum = -1;

        while(true)
        {
            peopleSNum++;
            if(peopleSNum >= num)
            { peopleSNum -= (num); }

            if(peopleLeft == 1 && *(people+peopleSNum)!=0)
            { flag = *(people+peopleSNum);break; }

            if(*(people + peopleSNum) == 0)
            { continue; }

            count++;

            if(count == quit)
            { *(people+peopleSNum) = 0 ; count = 0 ; peopleLeft--;}
        }

        cout<<"-------------------------------------"<<endl;
        cout<<"最后幸存下来的是【"<<flag<<"】号玩家!Congradulations!"
            <<"\n\n无畏的勇士呐，要再来一局吗？ヽ(•̀ω•́ )ゝ"
            <<"\n（1.Yes 2.No):";


        cin>>yesOrNo;
        while(yesOrNo!=1 && yesOrNo!=2)
        {
            cout<<"\n不要调皮哦！请再次选择!(ง •̀_•́)ง\n（1.再来十局！ 2.怕了怕了）：";
            cin>>yesOrNo;
        }

        replay = (yesOrNo==1)? true : false;
        
        if(yesOrNo==2)
            cout<<"\n欢迎下次再玩！"<<endl;
    }

}
