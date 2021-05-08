#define GO_MOTOR 12      // num pin of play motor
#define PUSH_HAND 8            //num pin of input push button to play the system
#define MICRO_SWICH 2            //num pin of input final whole circle (indicator)
#define DIRECTION_SELECTOR  6 //up or down
#define DIRECTION_OUT 10 // input to driver

void setup() 
{
  pinMode(GO_MOTOR, OUTPUT);
  pinMode(DIRECTION_OUT, OUTPUT);
  pinMode(DIRECTION_SELECTOR, INPUT_PULLUP);
  pinMode(PUSH_HAND, INPUT_PULLUP);
  pinMode(MICRO_SWICH, INPUT_PULLUP);
  
  attachInterrupt(digitalPinToInterrupt(MICRO_SWICH), AFTER_ONE_CIRCLE, RISING );//the micro swich off the motor after one circle
}

void AFTER_ONE_CIRCLE()// off motor in two directions,by interrupt MICRO_SWICH
{
  digitalWrite(GO_MOTOR, LOW); 
}

void PLAY()
{
  if(digitalRead(DIRECTION_SELECTOR)) //if DIRECTION_SELECTOR 'on'==1, go up
    digitalWrite(DIRECTION_OUT, LOW);
 else
   digitalWrite(DIRECTION_OUT, HIGH);

  digitalWrite(GO_MOTOR, HIGH);
  
}

void loop() 
{
  while(!digitalRead(PUSH_HAND));//waiting to the user's clicking 
  delayMicroseconds(50000);
  PLAY();
}

