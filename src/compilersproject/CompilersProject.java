package compilersproject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CompilersProject
{  
    static int index = 0;
    
    public static void checkComments (String s)
    {
        char [] a = s.toCharArray();
        int j= index;
        String str = "";
        str+=a[index];
        if( a[index] == '{')
        {
            while(j+1< a.length && a[j+1]!='}')
            {
                j++;
                str+=a[j];
            }
            System.out.println(str+'}' + "  " +"Comment");
            index += str.length()+1;
        }
    }
    
    public static void checkWords(String s)
    {
       char [] a = s.toCharArray();
       boolean flag =false;
       String [] words = new String[] {"if","then","else","end","repeat","until","read","write"};
       String str1 = "";
       String str2 ="";
       int j = index ;

       if(Character.isLetter(a[index]))
       {
           while(j<a.length && Character.isLetter(a[j])&& (a[j] != ' '))
           {   str1+=a[j];
               j++;
           }
           
           if(Character.isDigit(a[j]))
           {
               str2 =str1;
               while(Character.isLetterOrDigit(a[j]))
               {
                   str2+=a[j];
                   j++;
               }
            index+=str2.length(); 
            System.out.println(str2+ "  " + "error");
           }
           
           else
           {
                for(int k=0; k<words.length;k++)
                {
                    if(str1.equals(words[k]))
                    {
                        System.out.println(str1+ "  " + "Reserved Word");
                        flag = true;
                        break;
                    }    
                }
                if(!flag)
                    System.out.println(str1+ "  " + "Identifier");
                index+=str1.length(); 
            }
        }
    }
    
    public static void checkNumbers (String s)
    {
       char [] a = s.toCharArray();
       String str1 = "";
       String str2="";
       int j=index;
       
       if(Character.isDigit(a[index]))
       {
           while(j <a.length && Character.isDigit(a[j]) && !Character.isWhitespace(a[j]))
           {
               str1+=a[j];
               j++;
           }
               
           if(Character.isLetter(a[j]))
           {
               str2 =str1;
               while(Character.isLetterOrDigit(a[j]))
               {
                   str2+=a[j];
                   j++;
               }
            index+=str2.length(); 
            System.out.println(str2+ "  " + "error");
           }
           else
           {
               System.out.println(str1 + "   "+ "Number");
               index+=str1.length();
           }
       }
    }
    
    public static void checkSymbols (String s)
    {
       char [] a = s.toCharArray();
       char [] symbols = new char []{'+','-','*','/','=','<','(',')',';'};
      
           for (int j=0; j<symbols.length;j++)
           {
              if(a[index] == symbols[j])
              {
                  System.out.println(a[index]+ "  " + "Symbol");
                  return;
              }
              
              else 
                    if(a[index] == ':'&& a[index+1] == '=')
                    {
                        System.out.print(a[index]);
                        System.out.println(a[index+1]+ "  " + "Symbol");
                        index+=2;
                        return;      
                    }     
           } 
            if(!Character.isLetterOrDigit(a[index]) && !Character.isWhitespace(a[index]))
                    System.out.println(a[index] + "  " + "error");
                
    }
                
    public static void main(String[] args) 
    {        
        try 
        {  
           FileReader fr = new FileReader("Input.txt");
           BufferedReader br = new BufferedReader(fr);
           String line = br.readLine();
           String output ="";
           while(line!=null)
           {
                output+=line;
                output+= System.lineSeparator();
                line =  br.readLine();
           }
           br.close();
           fr.close();
           
            for(index=0 ; index < output.length(); index++)
            {
                checkComments(output);
                checkWords(output);
                checkSymbols(output);
                checkNumbers(output);
            }
        }       
        catch (IOException ex){}  
    }    
}