int n = 0;
void setup() {
  // put your setup code here, to run once:
  Serial.begin (9600);
  TCCR1A = 0;
  TCCR1B = 1;
  TCNT1H = 255;
  TCNT1L = duzo jakoss aaa;
  
  // dla tccrib 2, n zwiekszane 15 razy na sek, dla 4- 1, dla 5- 0, dla 3- 2, dla 1- 122
  TIMSK = (1<<TOIE1)|TIMSK;
  SREG = SREG|128;

}

void loop() {
  // put your main code here, to run repeatedly: 
  Serial.println(n);
  n = 0;
  delay(1000);
  
}

ISR(TIMER1_OVF_vect) {
  
  n++;
}  
