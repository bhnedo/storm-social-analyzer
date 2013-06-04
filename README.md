storm-social-analyzer
=====================

Simple application which uses Storm data stream processing system to analyze social data.

It uses Facebook API to fetch friend statuses and send them to RabbitMQ queue.

The the following topology is built:
   
   - AmqpStatusEmitterSpout: consumes messages from the queue and emits tuples with status and publisher.
   - StatusSplittingBolt: splits statuses into segments (words) 
   - StatusSegmentatorBolt: count distinct segments and send them via websocket to browser

