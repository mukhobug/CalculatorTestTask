import java.util.Objects;
import java.util.Scanner;

public class Calculator {
    public static void main(String [] args) throws Exceptions {
        int a,b,answer;
        Scanner scan=new Scanner(System.in);
        System.out.println(" Введите пример по типу:\na + b, a - b, a * b, a / b, где 1<(a,b)<10\nримскими или арабскими цифрами:");
        String input=scan.nextLine();
        scan.close();
        String [] split=input.split(" ");
        if(split.length>3)
            throw new Exceptions(" ошибка, формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *).");
        if(split.length<3)
            throw new Exceptions(" ошибка, строка не является математической операцией.");
        int t=0;
        for (int x=0; x<=2; x=x+2) {
            for (RimNumbers p: RimNumbers.values()){
                if (Objects.equals(split[x], p.name())){
                    t++;
                    break;
                }
            }
        }
        if(t==1)
            throw new Exceptions(" ошибка, числа из разных систем счисления или условие 1<(a,b)<10 не выполнено.");
        if (t==2){
            a=RimNumbers.valueOf(split[0]).getTranslate();
            b=RimNumbers.valueOf(split[2]).getTranslate();
            answer=cal(a,b,split[1]);
            if(answer<1)
                throw new Exceptions(" ошибка, ответ меньше единицы.");
            String dec="";
            String ed="";
            for(RimNumbers p: RimNumbers.values()){
                if(answer-answer%10==p.getTranslate()){
                    dec=p.name();
                }
            }
            for(RimNumbers p: RimNumbers.values()){
                if(answer%10==p.getTranslate()){
                    ed=p.name();
                }
            }
            System.out.println("Ответ:"+dec+ed);
        }
        else {
            try {
                a=Integer.parseInt(split[0]);
                b=Integer.parseInt(split[2]);
            }catch (NumberFormatException e){
                throw new Exceptions(" ошибка, неверный формат операндов");
            }
            answer=cal(a,b,split[1]);
            System.out.println("Ответ:" +answer);
            }
        }

        static int cal(int a, int b, String c) throws Exceptions {
            int answer;
            if (a>10||b>10||a<1||b<1)
                throw new Exceptions(" ошибка, условие 1<(a,b)<10 не выполнено.");
            switch (c) {
                    case "+" -> answer = (a + b);
                    case "-" -> answer = (a - b);
                    case "*" -> answer = (a * b);
                    case "/" -> answer = (a / b);
                    default -> throw new Exceptions(" ошибка, неверный оператор.");
                }
            return answer;
        }
    }