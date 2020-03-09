#include <iostream>
using namespace std;

int luckyBoy();
void Tips();

int main()
{
    Tips();
    luckyBoy();
}

void Tips()//提示部分
{
    cout<<"\n亲爱的勇士呐！欢迎来到幸存者游戏！(๑‾ ꇴ ‾๑)"
        <<"\n--------------------------------------"
        <<"\n游戏规则如下：\n游戏玩家投票选择一个不幸数字，然后为每名玩家分配随机连续的号码"
        <<"\n随后从一号玩家开始轮流报数"
        <<"\n报到不幸数字的玩家淘汰出局，由下一位玩家再次从'1'开始报数，直至决胜出一名幸运玩家！\n";
}


int luckyBoy()//核心算法
{
    bool replay = true;//用于判断玩家是否想重玩游戏（跳出while循环的判断条件）
    while(replay)
    {
        int yesOrNo;  //用户输入'1'时replay判断为true，'2'时为false，触发break;跳出总循环
        if(yesOrNo == 1)
            cout<<"\n-----------------";

        cout<<"\n请输入玩家人数：";
        int num;
        cin>>num;  //用来记录人数
        cout<<"请输入投票得出的不幸数字：";
        int quit;
        cin>>quit;//quit：报出quit的玩家退出游戏

        int player[num];
        for(int i = 0;i < num;i++)
            player[i] = i+1;  //数组玩家获取编号，'1'号玩家即player[0]，如果玩家退出，所存内容清空为'0'

        int theLuckyBoysNum = 0;//记录最后一名玩家的数字

        int peopleLeft = num ,count = 0;//记录剩余人数，count用于记录所报的数
        int peopleSNum = -1;//初始化数组下标

        while(true)
        {
            peopleSNum++;  //下标增加
            if(peopleSNum >= num)  //判断下标是否大于数组最大位置的索引，注意：num为用户输入游戏总人数，数组下标最大为num-1
            { peopleSNum -= (num); } //如果大于最大位置，那么索引位置从'0'开始

            if(peopleLeft == 1 && *(player+peopleSNum)!=0) //判断是否只有剩余只有一个玩家，及此时索引位置储存的数字是否为'0'
            { theLuckyBoysNum = *(player+peopleSNum);break; }//记录最后一名储存内容不为'0'的玩家此时剩余的数字，eg:player[3]:4

            if(*(player + peopleSNum) == 0)//如果此时索引位置储存的数为'0'（eg:player[3]:0）则不执行后续内容，重新开始下一次循环
            { continue; }

            count++;//当此时索引位置存储的内容不为'0'时，计时器+1，即所报数字（eg：count为'2'时所报的数为2）

            if(count == quit)//判断此时count（报数）是否为退出数字
            { *(player+peopleSNum) = 0 ; count = 0 ; peopleLeft--;}//判断成功，清空此时索引位置下所存储的内容
                                                                  //（eg:player[3]=4 --> player[3]=0）计数器归'0'
                                                                  //剩余人数-1

        }

        cout<<"-------------------------------------"<<endl;
        cout<<"最后幸存下来的是【"<<theLuckyBoysNum<<"】号玩家!Congradulations!"
            <<"\n\n无畏的勇士呐，要再来一局吗？ヽ(•̀ω•́ )ゝ"
            <<"\n（1.Yes 2.No!):";


        cin>>yesOrNo;
        while(yesOrNo!=1 && yesOrNo!=2)//移除除1、2以外的数字造成的影响
        {
            cout<<"\n不要调皮哦！请再次选择!(ง •̀_•́)ง\n（1.再来十局！ 2.怕了怕了）：";
            cin>>yesOrNo;
        }

        replay = (yesOrNo==1)? true : false;//若用户输入的yesOrNo为'1'，repay则为true，重新开始游戏，否则退出大循环
        if(yesOrNo==2)
            cout<<"\n欢迎下次再玩！"<<endl;
    }
}
