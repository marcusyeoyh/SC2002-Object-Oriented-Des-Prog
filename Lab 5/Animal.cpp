#include <iostream>
#include <string>
using namespace std;
enum COLOR
{
    Green,
    Blue,
    White,
    Black,
    Brown
};
class Animal
{
public:
    Animal() : _name("unknown")
    {
        cout << "constructing Animal object " << _name << endl;
    }
    Animal(string n, COLOR c) : _name(n), _color(c)
    {
        cout << "constructing Animal object " << endl;
        cout << "Animal name is:  " << _name << endl;
        switch (_color)
        {
        case 0:
            cout << "Animal color is: Green " << endl;
            break;
        case 1:
            cout << "Animal color is: Blue " << endl;
            break;
        case 2:
            cout << "Animal color is: White " << endl;
            break;
        case 3:
            cout << "Animal color is: Black " << endl;
            break;
        case 4:
            cout << "Animal color is: Brown " << endl;
            break;
        }
    }
    ~Animal()
    {
        cout << "destructing Animal object " << _name << endl;
    }
    virtual void speak()
    {
        cout << "Animal speaks " << endl;
    }
    virtual void move() = 0;

protected:
    string _name;
    COLOR _color;
};

class Mammal : public Animal
{
public:
    void eat() const
    {
        cout << "Mammal eat " << endl;
    }
    Mammal(string s, COLOR c) : Animal(s, c)
    {
        cout << "constructing Mammal object " << endl;
    }
    Mammal(){
        cout << "constructing Mammal object " << endl;
    }
    ~Mammal()
    {
        cout << "destructing Mammal object " << _name << endl;
    }
    virtual void speak()
    {
        cout << "Mammal speaks " << endl;
    }
    void move()
    {
        cout << "Mammal moves " << endl;
    }
};

class Dog : public Mammal
{
protected:
    string Owner;

public:
    Dog(string s, COLOR c, string o) : Mammal(s, c), Owner(o)
    {
        cout << "constructing Dog object " << endl;
        cout << "Dog owner is:  " << Owner << endl;
    }
    Dog(){
    }
    ~Dog()
    {
        cout << "destructing Dog object " << _name << endl;
    }
    void speak()
    {
        cout << "Woof Woof" << endl;
    }
    void move()
    {
        cout << "Dog moves" << endl;
    }
};

class Cat : public Mammal
{
protected:
    string Owner;

public:
    Cat(string s, COLOR c, string o) : Mammal(s, c), Owner(o)
    {
        cout << "constructing Cat object " << endl;
        cout << "Cat owner is:  " << Owner << endl;
    }
    Cat(){}
    ~Cat()
    {
        cout << "destructing Cat object " << _name << endl;
    }
    void speak()
    {
        cout << "Cat meow" << endl;
    }
    void move()
    {
        cout << "Cat moves" << endl;
    }
};

class Lion : public Mammal
{
protected:
    string Owner;

public:
    Lion(string s, COLOR c, string o) : Mammal(s, c), Owner(o)
    {
        cout << "constructing Lion object " << endl;
        cout << "Lion owner is:  " << Owner << endl;
    }
    Lion(){}
    ~Lion()
    {
        cout << "destructing Lion object " << _name << endl;
    }
    void speak()
    {
        cout << "Lion roar" << endl;
    }
    void move()
    {
        cout << "Lion moves" << endl;
    }
};

int main()
{
    // Mammal *animalPtr = new Dog("Lassie", White, "Andy");
    // animalPtr->speak();
    // animalPtr->move();
    // //Dog dogi("Lassie", White, "Andy");
    // // Mammal *aniPtr = new Dog("Lassie", White, "Andy");
    // // aniPtr->speak();
    int select = 0;
    Mammal **zoo= new Mammal*[20];
    int Arraycount = 0;
    while (select<5)
    {
        cout << "Select the animal to send to Zoo : " << endl;
        cout << "(1) Dog (2) Cat (3) Lion (4) Move all animals (5) Quit" << endl;
        cin >> select;
        switch (select)
        {
        case 1:
        zoo[Arraycount] = new Dog("Dog", Green, "Marcus");
        Arraycount++;
        cout << "Dog has been sent to Zoo " << endl;
        break;
        case 2:
        zoo[Arraycount] = new Cat("Cat", Green, "Marcus");
        Arraycount++;
        cout << "Cat has been sent to Zoo " << endl;
        break;
        case 3:
        zoo[Arraycount] = new Lion("Lion", Green, "Marcus");
        Arraycount++;
        cout << "Lion has been sent to Zoo " << endl;
        break;
        case 4:
        for(int i = 0; i<Arraycount; i++){
            zoo[i]->speak();
            zoo[i]->move();
        }
        break;
        case 5:
        cout << "Ending Switch" << endl;
        break;
        }
    }
    for(int i = 0; i<Arraycount; i++){
        delete(zoo[i]);
    }
    cout << "Program exiting …. " << endl;

    return 0;
}
