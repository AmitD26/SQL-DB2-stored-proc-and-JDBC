create or replace procedure gen_salary_histogram
	(in binstart int, in binend int, in num_bins int)
language sql
begin
	declare sqlstate char(5) default '00000';
	declare binwidth int;
	declare i int;
	declare start_bin int;
	declare end_bin int;
	declare num_bin int;
	declare emp_sal int;
	declare bin_index int;
	declare c cursor for select salary from employee;
	commit;
	truncate table histogram immediate;
	set binwidth = (binend - binstart) / num_bins;
	set i = 0;
	while (i < num_bins) do
		set start_bin = binstart + i * binwidth;
		set end_bin = start_bin + binwidth;
		set num_bin = i + 1;
		set i = i + 1;
		insert into histogram (binnum, binstart, binend) values (num_bin, start_bin, end_bin);	
	end while;
	open c;
	fetch from c into emp_sal;
	while (sqlstate = '00000') do
		set bin_index = (emp_sal - binstart) / binwidth + 1;
		update histogram
			set frequency = frequency + 1
			where binnum = bin_index;
		fetch from c into emp_sal;
	end while;
end
