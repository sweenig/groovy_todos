import groovy.json.JsonSlurper
class GroovyTut{
  static void main(String[] args){
    def slurper = new JsonSlurper();
    def todos = slurper.parseText(new URL("http://jsonplaceholder.typicode.com/todos").getText());
    def summary = [:];
    for(todo in todos){summary.put(todo.'userId',[0,0,''])}
    for (user in slurper.parseText(new URL("http://jsonplaceholder.typicode.com/users").getText())){summary[user.'id'][2] = user.'username';}
    for(todo in todos){
      summary[todo.'userId'][0] += (todo.'completed' ? 1 : 0);
      summary[todo.'userId'][1]++;
    }
    for (record in summary){
      println(record.value[2] + " has " + record.value[1] + " todos, " + record.value[0] + " of which are completed giving him/her a completion percentage of " + (record.value[0]*100/record.value[1]) + '%.');
    }
  }
}
