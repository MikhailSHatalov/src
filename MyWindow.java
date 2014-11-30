import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.dialog.DialogButtons;
import com.googlecode.lanterna.terminal.Terminal;
import sun.plugin2.message.Message;
import java.util.ArrayList;
import java.util.Scanner;

public class MyWindow extends Window{
    int who1;
    int who2;
    Skeleton s1 = new Skeleton("Skeleton",10, 2);
    Skeleton s2 = new Skeleton("Child Skeleton", 5, 1);
    Wolf w1 = new Wolf("Big wolf",10, 2);
    Wolf w2 = new Wolf("Small wolf", 10, 1);
    Wizard wiz = new Wizard("Wizard", 25, 2);
    Hero p = new Hero("Sir Lancelot", 50, 5);
    ArrayList<Character> enemies = new ArrayList<Character>();
    //
    int who3;
    int who4;
    char i1 = 1;
    char j1 = 1;
    char enemy1 = 5;
    char enemy2 = 5;
    char enemy3 = 8;
    char enemy4 = 8;
    char[][] c = new char[10][10];
    Panel main = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
    public static class Character{
        String name;
        int hitpoints;
        int attack;
        public Character(String n, int h, int a)
        {
            name = n;
            hitpoints = h;
            attack = a;
        }
        public void attack(Character c){};
    }
    public static class Hero extends Character{
        int maxhitpoints = Hero.super.hitpoints;
        int arrows;
        int exp = 0;
        int move = 0;
        public Hero(String n,int h, int a)
        {
            super(n,h,a);
        }
        public void attack(Character enemy)
        {
            if (Hero.this.arrows > 0){
                --Hero.this.arrows;
                enemy.hitpoints -= 5;
                ++move;
                if (move % 3 == 0){
                    ++Hero.this.hitpoints;
                    System.out.println("+1 hitpoint for Hero. Hero now has " + Hero.this.hitpoints + " hp");
                }
                if (enemy.hitpoints < 0) enemy.hitpoints = 0;
                System.out.println("Hero attacks " + enemy.name + " with his bow. Enemy now has " + enemy.hitpoints + " hp");
                if (Hero.this.arrows == 1){
                    System.out.println("It is last arrow.");
                }
            } else {
                ++move;
                if (move % 3 == 0){
                    ++Hero.this.hitpoints;
                    System.out.println("+1 hitpoint for Hero. Hero now has " + Hero.this.hitpoints + " hp");
                }
                enemy.hitpoints -= attack;
                if (enemy.hitpoints < 0) enemy.hitpoints = 0;
                System.out.println("Hero attacks " + enemy.name + " with his sword. Enemy now has " + enemy.hitpoints + " hp");
            }
        }
    }
    public static class Wolf extends Character{
        public Wolf(String n,int h, int a)
        {
            super(n,h,a);
        }
        public void attack(Character hero)
        {
            hero.hitpoints -= attack;
            if (hero.hitpoints < 0) hero.hitpoints = 0;
            System.out.println(name + " bites " + hero.name + ". Hero now has " + hero.hitpoints + " hp");
        }
    }
    public static class Wizard extends Character {
        int mana;
        public Wizard(String n, int h, int a) {
            super(n, h, a);
        }
        public void attack(Character hero)
        {
            if (mana % 10 == 0 && mana != 0) {
                mana -= 10;
                hero.hitpoints -= 10;
                if (hero.hitpoints < 0) hero.hitpoints = 0;
                System.out.println(name + " damage inflicted by magic " + hero.name + ". Hero now has " + hero.hitpoints + " hp");
            } else {
                mana += 5;
                System.out.println("+5 mana for Wizard");
                hero.hitpoints -= attack;
                if (hero.hitpoints < 0) hero.hitpoints = 0;
                System.out.println(name + " hit " + hero.name + ". Hero now has " + hero.hitpoints + " hp");
            }
        }
    }
    public static class Skeleton extends Character {
        public Skeleton(String n,int h, int a)
        {
            super(n,h,a);
        }
        public void attack(Character hero)
        {
            hero.hitpoints -= attack;
            if (hero.hitpoints < 0) hero.hitpoints = 0;
            System.out.println(name + " bites " + hero.name + ". Hero now has " + hero.hitpoints + " hp");
        }
    }
    public class Game {
        Hero h;
        ArrayList<Character> enemies;
        public void play() {
            while (h.hitpoints > 0) {
                if (enemies.get(0).hitpoints <= 0) {
                    System.out.println(enemies.get(0).name + " dies");
                    enemies.remove(enemies.get(0));
                    h.exp += 2;
                }
                if (enemies.size() == 0) {
                    System.out.println("You are victorous");
                    h.hitpoints = h.maxhitpoints;
                    delc();
                    break;
                }
                for (Character enemy : enemies) {
                    enemy.attack(h);
                    if (h.hitpoints <= 0) {
                        System.out.println("Hero dies");
                        break;
                    }
                }
                if (h.hitpoints > 0)
                    h.attack(enemies.get(0));
                else
                    System.out.println("You are defeated");

            }
        }
    }
    public void start_e1(){
        enemies.add(s1);
        enemies.add(s2);
        p.arrows = 3;
        Game go = new Game();
        go.enemies = enemies;
        go.h = p;
        who1 = 1;
        go.play();
    }
    public void start_e2(){
        enemies.add(w1);
        enemies.add(w2);
        enemies.add(wiz);
        p.arrows = 3;
        wiz.mana = 20;
        Game go = new Game();
        go.enemies = enemies;
        go.h = p;
        who2 = 2;
        go.play();
    }
    public void m_loader(){
        for (char i = 0; i < c.length; i++) {
            for (char j = 0; j < c[i].length; j++) {
                c[i][j] = ' ';
                c[i][9] = 'W';
                c[i][0] = 'W';
                c[0][j] = 'W';
                c[j][9] = 'W';
                c[9][j] = 'W';
                c[enemy1][enemy2] = 'S';
                c[enemy3][enemy4] = 'Y';
                if (who3 == 1) c[enemy1][enemy2] = ' ';
                if (who4 == 1) c[enemy3][enemy4] = ' ';
                c[i1][j1] = '@';
                c[2][0] = 'D';
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();
        }
    }
    public void delc(){
        if (who1 == 1){who3 = 1;}
        if (who2 == 2){who4 = 1;}
    }
    public MyWindow(){
        super("Roguelike");
        main.addComponent(new Button("Load map", new Action() {
            @Override
            public void doAction() {
                m_loader();
            }
        }));
        main.addComponent(new Button("n", new Action() {
            @Override
            public void doAction() {
                    if (c[i1 - 1][j1] == 'W') {
                        System.out.println("There's wall!");
                        m_loader();
                    } else {
                        i1 -= 1;
                        m_loader();
                        if (c[i1 + 1][j1] == 'S' || c[i1 - 1][j1] == 'S' || c[i1][j1 + 1] == 'S' || c[i1][j1 - 1] == 'S'){
                            start_e1();
                        }
                        if (c[i1 + 1][j1] == 'Y' || c[i1 - 1][j1] == 'Y' || c[i1][j1 + 1] == 'Y' || c[i1][j1 - 1] == 'Y'){
                            start_e2();
                        }
                        if (c[i1 + 1][j1] == 'D' || c[i1 - 1][j1] == 'D' || c[i1][j1 + 1] == 'D' || c[i1][j1 - 1] == 'D'){
                            System.out.println("You see the door! Press 'Open the door' to win.");
                        }
                    }
            }
        }));
        main.addComponent(new Button("s", new Action() {
            @Override
            public void doAction() {
                if (c[i1 + 1][j1] == 'W') {
                    System.out.println("There's wall!");
                    m_loader();
                } else {
                    i1 += 1;
                    m_loader();
                    if (c[i1 + 1][j1] == 'S' || c[i1 - 1][j1] == 'S' || c[i1][j1 + 1] == 'S' || c[i1][j1 - 1] == 'S'){
                        start_e1();
                    }
                    if (c[i1 + 1][j1] == 'Y' || c[i1 - 1][j1] == 'Y' || c[i1][j1 + 1] == 'Y' || c[i1][j1 - 1] == 'Y'){
                        start_e2();
                    }
                    if (c[i1 + 1][j1] == 'D' || c[i1 - 1][j1] == 'D' || c[i1][j1 + 1] == 'D' || c[i1][j1 - 1] == 'D'){
                        System.out.println("You see the door! Press 'Open the door' to win.");
                    }
                }
            }
        }));
        main.addComponent(new Button("w", new Action() {
            @Override
            public void doAction() {
                if (c[i1][j1 - 1] == 'W') {
                    System.out.println("There's wall!");
                    m_loader();
                } else {
                    j1 -= 1;
                    m_loader();
                    if (c[i1 + 1][j1] == 'S' || c[i1 - 1][j1] == 'S' || c[i1][j1 + 1] == 'S' || c[i1][j1 - 1] == 'S'){
                        start_e1();
                    }
                    if (c[i1 + 1][j1] == 'Y' || c[i1 - 1][j1] == 'Y' || c[i1][j1 + 1] == 'Y' || c[i1][j1 - 1] == 'Y'){
                        start_e2();
                    }
                    if (c[i1 + 1][j1] == 'D' || c[i1 - 1][j1] == 'D' || c[i1][j1 + 1] == 'D' || c[i1][j1 - 1] == 'D'){
                        System.out.println("You see the door! Press 'Open the door' to win.");
                    }
                }
            }
        }));
        main.addComponent(new Button("e", new Action() {
            @Override
            public void doAction() {
                if (c[i1][j1 + 1] == 'W') {
                    System.out.println("There's wall!");
                    m_loader();
                } else {
                    j1 += 1;
                    m_loader();
                    if (c[i1 + 1][j1] == 'S' || c[i1 - 1][j1] == 'S' || c[i1][j1 + 1] == 'S' || c[i1][j1 - 1] == 'S'){
                        start_e1();
                    }
                    if (c[i1 + 1][j1] == 'Y' || c[i1 - 1][j1] == 'Y' || c[i1][j1 + 1] == 'Y' || c[i1][j1 - 1] == 'Y'){
                        start_e2();
                    }
                    if (c[i1 + 1][j1] == 'D' || c[i1 - 1][j1] == 'D' || c[i1][j1 + 1] == 'D' || c[i1][j1 - 1] == 'D'){
                        System.out.println("You see the door! Press 'Open the door' to win.");
                    }
                }
            }
        }));
        main.addComponent(new Button("Open the door", new Action() {
            @Override
            public void doAction() {
                if(c[i1 + 1][j1] == 'D' || c[i1 - 1][j1] == 'D' || c[i1][j1 + 1] == 'D' || c[i1][j1 - 1] == 'D'){
                    System.out.println("You are win!");
                    System.exit(0);
                } else {
                    System.out.println("You aren't near the door!");
                }
            }
        }));
        main.addComponent(new Button("Exit", new Action(){
            @Override
            public void doAction() {
                System.exit(0);
            }
        }));
        addComponent(main);
    }
}