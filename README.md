storm-social-analyzer
=====================

Simple application to illustrate Storm stream processing system analyzing social data.

It uses Facebook API to fetch friend statuses and send them to RabbitMQ queue.

Then the following topology is built:
   
   - AmqpStatusEmitterSpout: consumes messages from the queue and emits tuples with status and publisher.
   - StatusSplittingBolt: splits statuses into segments (words). 
   - StatusSegmentatorBolt: counts distinct segments and sends them via websockets to browser to show up top 20 most used words.

