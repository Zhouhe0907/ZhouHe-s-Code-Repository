#include <iostream>
using namespace std;

int theLastOne(int, int );
void luckyBoy();
bool leagalOrNot(int);


int main()
{
    cout<<"\n亲爱的勇士呐！欢迎来到幸存者游戏！(๑‾ ꇴ ‾๑)"
        <<"\n--------------------------------------"
        <<"\n游戏规则如下：\n游戏玩家投票选择一个不幸数字，然后为每名玩家分配随机连续的号码"
        <<"\n随后从一号玩家开始轮流报数"
        <<"\n报到不幸数字的玩家淘汰出局，由下一位玩家再次从'1'开始报数，直至决胜出一名幸运玩家！\n";

    luckyBoy();
}

void luckyBoy()
{
    bool replay = true;
    while(replay)
    {
        int yesOrNo = 0;
        if(yesOrNo == 1)
            cout<<"\n-------------------------------------";

        cout<<"\n请输入玩家人数：";
        int num;
        cin>>num;  //用来记录人数

        cout<< ((num == 1) ? "一个人不就是又死又活了吗!？重来重来！ᕦ(ò_óˇ)ᕤ\n"
                           : (num == 0) ? "没人怎么玩游戏啊喂！重来重来！!(((￣へ￣井)\n"
                                        : (num < 0) ? "负人是什么鬼啊！！重来重来！Ծ‸ Ծ \n"
                                                    :"请输入投票得出的不幸数字：");

        if(leagalOrNot(num))//输入不合法，退回
            continue;

        int quit;
        cin>>quit;//quit：报出quit的玩家退出游戏
        if(leagalOrNot(quit))
            while (leagalOrNot(quit))
            {
                cout<<"这什么奇奇怪怪的数字？！再来：";
                cin>>quit;
            }
        cout<<"-------------------------------------"<<endl;
        cout<<"最后幸存下来的是【"<<theLastOne(num,quit)<<"】号玩家!Congradulations!"
            <<"\n\n无畏的勇士呐，要再来一局吗？ヽ(•̀ω•́ )ゝ"
            <<"\n（1.Yes 2.No):";


        cin>>yesOrNo;
        cout<<"-------------------------------------";
        while(yesOrNo!=1 && yesOrNo!=2)
        {
            cout<<"\n不要调皮哦！请再次选择!(ง •̀_•́)ง\n（1.再来十局！ 2.怕了怕了）：";
            cin>>yesOrNo;
        }

        replay = yesOrNo == 1;
        if(yesOrNo==2)
            cout<<"\n欢迎下次再玩！"<<endl;
    }

}
//
bool leagalOrNot(int a)//return true the input is legal,
{
    return a <= 1;
}

int theLastOne(int n, int q)
{
    int arr[n];

    for(int j = 0;j < n;j++)
        arr[j] = j + 1;

    int m = 0;//the number of people quited

    int count = 0; 

    int i = -1;//the index of the array 'arr[]'

    while(true)
    {
        i++;

        if(*(arr+i) != 0) // 0 is the flag of peop;e quited
        {
            count++;

            if(m == n - 1 && *(arr+i) != 0)
                return *(arr+i);

            if(count == q)
            {
                *(arr+i) = 0;
                count = 0;
                m++;
            }
        }

        if(i == n - 1)//Reset the index 'n' to 0 if the 'for' loop reaches the end of the array
            i = -1;
    }
}
