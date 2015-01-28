import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;


public class Main {
	
	static class LinearSearchMap {
		public String[] x;
		public String[] y;
		
		public LinearSearchMap(int size) {
			x = new String[size];
			y = new String[size];
		}
		
		public String get(String str) {
			for (int i = 0; i < x.length; i++)
				if (x[i].equals("\"" + str + "\""))
					return y[i].replace("\"", "");
			return "FAIL(" + str + ")";
		}
	}
	
	static LinearSearchMap readRoster(String rosterFile) {
		
		List<String> fileLines = new ArrayList<String>();
		
		try {
			fileLines = FileUtils.readLines(new File(rosterFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		LinearSearchMap lsm = new LinearSearchMap(fileLines.size()-1);
		
		//Skip first line - header
		//col[2] = RID
		//col[6] = SUBJID
		for (int i = 1; i < fileLines.size(); i++) {
			String[] cells = fileLines.get(i).split(",");
			lsm.x[i-1] = cells[2];
			lsm.y[i-1] = cells[6];
		}
		
		return lsm;
	}
	
	
	public static void main(String[] args) {
		args = new String[8];
		
		args[0] = "-ids";
		args[1] = "15,86,184,187,194,196,290,300,324,335,397,413,429,442,449,478,479,505,507,553,559,568,579,606,607,625,633,677,814,816,828,830,886,916,926,956,963,969,1009,1018,1066,1070,1072,1104,1117,1121,1123,1126,1138,1149,1190,1191,1206,1209,1222,1227,1241,1247,1250,1251,1253,1256,1261,1262,1267,1268,1280,1286,1289,1292,1293,1331,4001,4004,4005,4010,4012,4014,4021,4024,4028,4029,4030,4032,4034,4036,4037,4041,4042,4043,4050,4051,4053,4054,4057,4058,4059,4060,4061,4063,4067,4071,4072,4075,4076,4077,4079,4080,4084,4090,4093,4094,4096,4097,4100,4104,4105,4114,4115,4120,4125,4127,4128,4131,4133,4134,4139,4143,4146,4149,4150,4151,4153,4157,4158,4159,4162,4167,4168,4169,4170,4171,4172,4173,4174,4175,4185,4187,4188,4189,4192,4194,4197,4198,4199,4200,4201,4202,4203,4205,4206,4208,4209,4210,4211,4212,4213,4216,4217,4218,4219,4220,4225,4226,4229,4232,4235,4240,4241,4243,4250,4251,4252,4254,4255,4256,4257,4258,4259,4262,4263,4266,4268,4269,4270,4272,4274,4275,4276,4277,4278,4280,4287,4291,4292,4293,4294,4297,4300,4302,4303,4308,4309,4310,4311,4312,4313,4320,4324,4328,4331,4332,4335,4337,4339,4343,4345,4346,4352,4353,4356,4357,4359,4360,4363,4365,4366,4367,4371,4377,4381,4382,4383,4384,4388,4389,4390,4391,4392,4393,4394,4396,4399,4400,4401,4402,4404,4405,4406,4408,4410,4417,4419,4420,4421,4422,4424,4427,4428,4429,4432,4433,4434,4438,4443,4444,4446,4447,4448,4449,4453,4455,4456,4458,4463,4464,4466,4467,4475,4476,4477,4480,4482,4483,4485,4488,4491,4494,4496,4498,4500,4501,4502,4506,4507,4510,4512,4513,4514,4515,4517,4520,4521,4522,4530,4539,4540,4542,4545,4546,4547,4549,4553,4556,4558,4565,4566,4571,4576,4579,4584,4585,4586,4587,4589,4590,4591,4594,4595,4597,4598,4599,4601,4604,4605,4607,4609,4611,4612,4613,4614,4615,4620,4623,4624,4625,4626,4629,4631,4632,4635,4636,4637,4638,4641,4643,4645,4646,4652,4653,4654,4661,4671,4672,4674,4675,4676,4678,4679,4680,4686,4689,4696,4707,4708,4712,4713,4714,4718,4720,4721,4722,4723,4728,4729,4730,4733,4739,4741,4742,4743,4745,4746,4750,4757,4762,4764,4767,4769,4770,4777,4780,4782,4791,4796,4799,4803,4804,4813,4814,4815,4816,4817,4820,4825,4827,4832,4835,4845,4849,4852,4853,4855,4856,4863,4867,4868,4869,4871,4872,4873,4874,4876,4877,4883,4885,4889,4891,4893,4898,4899,4902,4903,4904,4905,4907,4909,4910,4912,4917,4918,4919,4920,4921,4925,4929,4936,4940,4941,4947,4949,4951,4952,4960,4971,4974,4982,4987,4989,5004,5007,5012,5014,5017,5019,5026,5027,5040,5047,5054,5057,5063,5087,5099,5138";
		args[2] = "-rf";
		args[3] = "/Users/alex/Downloads/ROSTER.csv";
		args[4] = "-of";
		args[5] = "/Users/alex/Desktop/subids.csv";
		args[6] = "-sep";
		args[7] = ",";
		
		String ids = null;
		String rosterFile = null;
		String outFile = null;
		String sep = "\n";
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-ids")) 
				ids = args[i+1];
			else if (args[i].equals("-rf"))
				rosterFile = args[i+1];
			else if (args[i].equals("-of"))
				outFile = args[i+1];
			else if (args[i].equals("-sep"))
				sep = args[i+1];
		}
	
		//Read roster file
		LinearSearchMap ridToSubjid = readRoster(rosterFile);
	
		StringBuilder out = new StringBuilder();
		
		String[] idCsv = ids.split(",");
		for (int i = 0; i < idCsv.length; i++) {
				out.append(ridToSubjid.get(idCsv[i].trim()));
				if (i != idCsv.length - 1)
				out.append(sep);
		}

		System.out.println("Size: " + idCsv.length);
		
		//Write output file
		
		System.out.println(out.toString());
	}
}
