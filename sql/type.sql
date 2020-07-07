-- type:all
EXPLAIN SELECT * FROM vote_record WHERE group_id =2;

-- type:index 扫描顺序是按照索引的顺序,这种扫描根据索引然后回表取数据
EXPLAIN SELECT * FROM vote_record ORDER BY id;

-- type:range 有范围的索引扫描，相对于index的全索引扫描，它有范围限制
EXPLAIN SELECT * FROM vote_record WHERE id BETWEEN 1 AND 10 ;

-- type:ref 查找条件列使用了索引而且不为主键和unique,这样即使使用索引快速查找到了第一条数据，仍然不能停止，要进行目标值附近的小范围扫描
EXPLAIN SELECT * FROM vote_record WHERE vote_id ='734';

-- type:const
EXPLAIN SELECT * FROM vote_record WHERE id =1;