/*
 * Copyright 2019 InfAI (CC SES)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.infai.seits.sepl.operators.Config;
import org.infai.seits.sepl.operators.Message;
import org.infai.seits.sepl.operators.OperatorInterface;
import org.json.JSONTokener;

public class Filter implements OperatorInterface {
    private Object value;

    public Filter(String valueString) {
        this.value = new JSONTokener(valueString).nextValue();
    }

    @Override
    public void run(Message message) {
        try{
            boolean ok = false;
            if(this.value instanceof String){
                ok = this.value.equals(message.getInput("value").getString());
            }else if(this.value instanceof Double){
                ok = this.value.equals(message.getInput("value").getValue());
            }else if(this.value instanceof Integer){
                ok = this.value.equals(message.getInput("value").getValue().intValue());
            }else if(this.value instanceof Float){
                ok = this.value.equals(message.getInput("value").getValue().floatValue());
            }
            if(ok){
                message.output("filtered", true);   //signal library to send message to next receiver
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void config(Message message) {
        message.addInput("value");
    }
}
